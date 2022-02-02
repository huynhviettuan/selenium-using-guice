package drivermanager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverManager extends DriverManager {

    @Override
    public void createDriver () {
        String host = "localhost";
        DesiredCapabilities dc;

        if(System.getProperty("REMOTE.PLATFORM") != null &&
                System.getProperty("REMOTE.PLATFORM").equalsIgnoreCase("firefox")){
            dc = DesiredCapabilities.firefox();
        }else{
            dc = DesiredCapabilities.chrome();
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String completeUrl = "http://" + host + ":4444/wd/hub";
        try {
            driver = new RemoteWebDriver(new URL(completeUrl), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
