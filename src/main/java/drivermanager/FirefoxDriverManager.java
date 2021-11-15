package drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", "C:\\Windows\\temp");
        options.addPreference("browser.download.useDownloadDir", true);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        options.addPreference("pdfjs.disabled", true);
        options.addArguments("-private");
        driver = new FirefoxDriver(options);
    }
}
