package element;

import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacadeElementImp implements FacadeElement {

    private WebDriver driver;
    private ElementInfo info;
    private String formattedValue;
    private Select select;

    public FacadeElementImp(WebDriver driver, ElementInfo info) {
        this.driver = driver;
        this.info = info;
    }

    private WebElement getElement(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        switch (info.getCondition()) {
            case VISIBILITY:
            default:
                return wait.until(ExpectedConditions.visibilityOfElementLocated(getBy()));
            case PRESENCE:
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy()));
        }
    }
    
    private By getBy() {
        switch (info.getType()) {
            case ID:
                return By.id(formattedValue);
            case XPATH:
                return By.xpath(formattedValue);
        }
        return null;
    }

    @Override
    public void hasText(String name) {
        this.formattedValue = String.format(this.info.getValue(), name);
    }

    @Override
    public void click() {
        this.click(Constants.DEFAULT_TIMEOUT);
    }

    @Override
    public void type(String value) {
        this.getElement(Constants.DEFAULT_TIMEOUT).sendKeys(value);
    }

    @Override
    public void typeAndEnter(String value) {
        this.getElement(Constants.DEFAULT_TIMEOUT).sendKeys(value);
        this.getElement(Constants.DEFAULT_TIMEOUT).sendKeys(Keys.ENTER);
    }

    @Override
    public void select(String value) {
        select = new Select(getElement(Constants.DEFAULT_TIMEOUT));
        select.selectByVisibleText(value);
    }

    @Override
    public void click(long timeout) {
        getElement(timeout).click();
    }
}
