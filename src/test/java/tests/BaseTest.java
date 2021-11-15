package tests;

import com.google.inject.Inject;
import common.BrowserInteractions;
import common.Constants;
import drivermanager.DriverModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;

@Guice(modules = {
        DriverModule.class
})
public class BaseTest {

    @Inject
    BrowserInteractions browserInteractions;

    @BeforeClass
    void setup() {
        browserInteractions.goTo(Constants.DEFAULT_URL);
    }

    @AfterClass
    void tearDown() {
        browserInteractions.closeBrowser();
    }
}
