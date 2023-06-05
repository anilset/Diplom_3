package site.nomoreparties.stellarburgers.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import site.nomoreparties.stellarburgers.config.UserConfig;
import site.nomoreparties.stellarburgers.pom.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static site.nomoreparties.stellarburgers.config.UserConfig.*;
import static site.nomoreparties.stellarburgers.pom.HeaderItems.LOGO;

public class RegistrationTest {
    WebDriver driver;
    String name;
    String email;
    String pwd;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGO));
        driver.manage().window().maximize();
        name = UserConfig.getRandomString(10);
        email = UserConfig.getRandomLogin();
        pwd = UserConfig.getRandomPwd();
    }

    @Test
    @DisplayName("Проверка регистрации")
    public void registrationPositiveTest() {
        Boolean isRegistered = new HeaderItems(driver)
                .clickYourAccount()
                .clickRegistrationButton()
                .register(name, email, pwd)
                .isLoginPageVisible();
        assertTrue(isRegistered);
    }

    @Test
    @DisplayName("Проверка невозможности регистрации и ошибки с паролем длиной менее 6 символов")
    public void registrationNegativeTest() {
        pwd = UserConfig.getRandomString(5);
        RegistrationPage registrationPage= new HeaderItems(driver)
                .clickYourAccount()
                .clickRegistrationButton();
                registrationPage.register(name, email, pwd);
        assertTrue(registrationPage.isPwdErrorVisible());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        RestAssured.baseURI = BASE_URI;
        ValidatableResponse login = UserConfig.login(email, pwd);
        if(login.extract().statusCode() == 200) {
            String accessToken = UserConfig.getAccessToken(login);
            UserConfig.deleteUser(accessToken);
        }
    }
}
