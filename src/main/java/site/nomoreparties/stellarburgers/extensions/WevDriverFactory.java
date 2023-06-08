package site.nomoreparties.stellarburgers.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.config.BrowserConfig;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.config.UserConfig.URL;
import static site.nomoreparties.stellarburgers.pom.HeaderItems.LOGO;

public class WevDriverFactory {

    public static WebDriver getDriver() {
        WebDriver driver;
        String browserName = BrowserConfig.getBrowserValue();
        switch (browserName) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "yandex":
                driver = getYandexDriver();
                break;
            default:
                throw new RuntimeException("This browser is not supported yet");
        }
        driver.navigate().to(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGO));
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver getYandexDriver() {
        String userDir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", userDir + "/src/main/resources/yandexdriver");
        return new ChromeDriver();
    }
}

