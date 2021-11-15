package drivermanager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DriverManagerProvider implements Provider<DriverManager> {


    @Override
    public DriverManager get() {
        Properties fileProperties = new Properties();
        try {
            fileProperties.load(new FileReader(System.getProperty("user.dir") + "/res/config/web.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String platform = System.getProperty("web.platform") != null ? System.getProperty("web.platform") : fileProperties.getProperty("web.platform");
        Injector injector = Guice.createInjector(new DriverModule());
        DriverManager manager = injector.getInstance(Key.get(DriverManager.class, Names.named(platform)));
        return manager;
    }
}
