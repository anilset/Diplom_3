package site.nomoreparties.stellarburgers.tests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.UserConfig;
import site.nomoreparties.stellarburgers.extensions.WevDriverFactory;
import site.nomoreparties.stellarburgers.pom.Header;
import site.nomoreparties.stellarburgers.pom.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static site.nomoreparties.stellarburgers.config.UserConfig.BASE_URI;

public class RegistrationTest {
    WebDriver driver;
    String name;
    String email;
    String pwd;

    @BeforeEach
    public void setUp(){
        driver = WevDriverFactory.getDriver();
        name = UserConfig.getRandomString(10);
        email = UserConfig.getRandomLogin();
        pwd = UserConfig.getRandomPwd();
    }

    @Test
    @DisplayName("Проверка регистрации")
    public void registrationPositiveTest() {
        Boolean isRegistered = new Header(driver)
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
        RegistrationPage registrationPage= new Header(driver)
                .clickYourAccount()
                .clickRegistrationButton();
                registrationPage.register(name, email, pwd);
        assertTrue(new RegistrationPage(driver).isPwdErrorVisible());
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
