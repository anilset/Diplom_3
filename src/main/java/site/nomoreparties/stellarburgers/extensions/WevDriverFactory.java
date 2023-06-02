package site.nomoreparties.stellarburgers.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.config.UserConfig.URL;
import static site.nomoreparties.stellarburgers.pom.HeaderItems.LOGO;

public class WevDriverFactory {

        public static WebDriver getDriver() {
            WebDriver driver;
            String browserName = System.getenv().get("browser");
            if(browserName == null) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver");
                    driver = new ChromeDriver();
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
}
