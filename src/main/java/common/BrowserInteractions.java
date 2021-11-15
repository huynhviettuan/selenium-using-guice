package common;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class BrowserInteractions {

    private static Logger logger = Logger.getLogger(BrowserInteractions.class);

    private final WebDriver driver;

    @Inject

    public BrowserInteractions(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void closeBrowser() {
        this.driver.quit();
    }

    public Actions actions() {
        return new Actions(driver);
    }

    public String captureFullScreenshot(String filename, String filepath) {
        logger.info("Capturing screenshot");
        String path = "";
        try {
            // Storing the image in the local system.
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(this.driver);
            File dest = new File(filepath + File.separator + filename);
            ImageIO.write(screenshot.getImage(), "PNG", dest);
            path = dest.getAbsolutePath();
        } catch (Exception e) {
            logger.error("An error occurred when capturing screen shot: " + e.getMessage());
        }
        return path;
    }

    public void delay(double timeInSecond) {
        try {
            Thread.sleep((long) (timeInSecond * 1000));
        } catch (Exception e) {
            logger.error("An error occurred when delay: " + e.getMessage());
        }
    }

    public void deleteCookie() {
        driver.manage().deleteAllCookies();
    }

    public Object execJavaScript(String script, Object... objs) {
        return ((JavascriptExecutor) driver).executeScript(script, objs);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public int getNumberOfWindows() {
        return driver.getWindowHandles().size();
    }

    public void maximizeBrowser() {
        try {
            logger.debug("Maximize browser");
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.error("An error occurred when maximizing browser" + e.getMessage());
        }
    }

    public void switchToNewWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void switchToWindow(int index) {
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
    }

    public void waitForAngularReady() {
        try {
            logger.debug("Wait for angular ready");
            WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                return (Boolean) (executor
                        .executeScript("return angular.element(document).injector().get('$http').pendingRequests.length === 0;"));
            });
        } catch (Exception e) {
            logger.error("waitForAngularReady: An error occurred when waitForAngularReady" + e.getMessage());
        } finally {
            logger.debug("End wait for angular ready");
        }
    }

    public boolean waitForCondition(Callable<Boolean> conditionEvaluator, Duration interval, Duration timeout) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(timeout).pollingEvery(interval);
        try {
            return wait.until(driver -> {
                try {
                    return conditionEvaluator.call();
                } catch (Exception e) {
                    System.out.println("DriverUtils::waitForCondition() -> " + e.getMessage());
                }
                return false;
            });
        } catch (TimeoutException e) {
            System.out.println("DriverUtils::waitForCondition() -> " + e.getMessage());
            return false;
        }
    }

    public void waitForJavaScriptIdle() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                //Boolean ajaxIsComplete = (Boolean) (executor.executeScript(
                //"if (typeof jQuery != 'undefined') { return jQuery.active == 0; } else {  return true; }"));
                Boolean domIsComplete = (Boolean) (executor
                        .executeScript("return document.readyState == 'complete';"));
                return domIsComplete;
            });
        } catch (Exception e) {
            logger.error("waitForJavaScriptIdle: An error occurred when waitForJavaScriptIdle" + e.getMessage());
        }
    }

    public void waitForAjax() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                Boolean ajaxIsComplete = (Boolean) (executor.executeScript(
                        "if (typeof jQuery != 'undefined') { return jQuery.active == 0; } else {  return true; }"));
                Boolean domIsComplete = (Boolean) (executor
                        .executeScript("return document.readyState == 'complete';"));
                return domIsComplete && ajaxIsComplete;
            });
        } catch (Exception e) {
            logger.error("waitForAjax: An error occurred when waitForAjax" + e.getMessage());
        }
    }


}
