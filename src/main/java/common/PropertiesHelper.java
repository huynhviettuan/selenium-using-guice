package common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class PropertiesHelper {

    public static String readSetting(String name) {
        Reader reader = null;
        Properties properties = null;
        try {
            reader = new FileReader(System.getProperty("user.dir") + "/res/config/web.properties");
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.get(name).toString();
    }
}
