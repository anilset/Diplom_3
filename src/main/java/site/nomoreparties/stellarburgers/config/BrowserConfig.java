package site.nomoreparties.stellarburgers.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BrowserConfig {
    private static final String userRoot = System.getProperty("user.dir");
    private static final String filePath = userRoot + "/src/main/resources/browser.properties";
    private static Properties browserValue = new Properties();


    public static FileInputStream getFileWithBrowserValue() throws FileNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        return file;
    }


    public static String getBrowserValue() {
        String browserName;
        try {
            browserValue.load(getFileWithBrowserValue());
            browserName = browserValue.getProperty("browser");
        } catch (IOException exception) {
            browserName = "chrome";
        }
        return browserName;
    }

   /* public static void main(String[] args) {
        System.out.println(getBrowserValue());
        System.out.println(userRoot);
    }*/
}
