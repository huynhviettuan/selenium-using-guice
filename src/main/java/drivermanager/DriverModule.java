package drivermanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DriverManager.class).toProvider(DriverManagerProvider.class).in(Scopes.SINGLETON);
        bind(DriverManager.class)
                .annotatedWith(Names.named("chrome"))
                .to(ChromeDriverManager.class)
                .in(Scopes.SINGLETON);
        bind(DriverManager.class)
                .annotatedWith(Names.named("firefox"))
                .to(FirefoxDriverManager.class)
                .in(Scopes.SINGLETON);
        bind(DriverManager.class)
                .annotatedWith(Names.named("remote"))
                .to(RemoteWebDriverManager.class)
                .in(Scopes.SINGLETON);

        bind(String.class).annotatedWith(Names.named("platform")).toInstance(getPlatform());
    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }

    protected String getPlatform() {
        Properties fileProperties = new Properties();
        try {
            fileProperties.load(new FileReader(System.getProperty("user.dir") + "/res/config/web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.getProperty("BROWSER") != null ? System.getProperty("BROWSER") : fileProperties.getProperty("BROWSER");
    }
}
