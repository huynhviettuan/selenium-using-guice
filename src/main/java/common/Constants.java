package common;

public class Constants {

    public static int DEFAULT_TIMEOUT = Integer.parseInt(PropertiesHelper.readSetting("default.timeout"));
    public static String DEFAULT_URL = PropertiesHelper.readSetting("default.url");
}
