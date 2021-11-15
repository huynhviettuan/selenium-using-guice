package pages;

import annotation.FindElement;
import com.google.inject.Inject;
import element.ElementFactory;
import element.FacadeElement;
import element.LocatorType;
import org.openqa.selenium.WebDriver;

public class PaymentGatewayPage {

    @Inject
    public PaymentGatewayPage(WebDriver driver) {
        ElementFactory.initElements(driver, this);
    }

    @FindElement(type = LocatorType.XPATH, value = "//input[@value='%s']")
    private FacadeElement btnBuyNow;

    @FindElement(type = LocatorType.XPATH, value = "//select[@name='quantity']")
    private FacadeElement cbbQuantity;

    public void orderBabiesSoftToy(String quantity) {
        cbbQuantity.hasText(quantity);
        btnBuyNow.hasText("Buy Now");
        btnBuyNow.click();
    }
}
