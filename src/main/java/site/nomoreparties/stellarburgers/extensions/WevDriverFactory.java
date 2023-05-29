package site.nomoreparties.stellarburgers.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static site.nomoreparties.stellarburgers.config.UserConfig.URL;
import static site.nomoreparties.stellarburgers.pom.Header.LOGO;

public class WevDriverFactory {

        public static WebDriver getDriver() {
            String browserName = System.getenv().get("browser");
            WebDriver driver;
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("This browser is not supported yet");
            }
            driver.navigate().to(URL);
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(LOGO));
            driver.manage().window().maximize();
            return driver;
        }
}
