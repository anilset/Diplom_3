import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.UserConfig;
import site.nomoreparties.stellarburgers.extensions.WevDriverFactory;
import site.nomoreparties.stellarburgers.pom.AccountPage;
import site.nomoreparties.stellarburgers.pom.Header;
import site.nomoreparties.stellarburgers.pom.HeaderItems;
import site.nomoreparties.stellarburgers.pom.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void registrationPositiveTest()  {
        HeaderItems header = new Header(driver);
        header.clickYourAccount()
                .clickRegistrationButton()
                .register(name, email, pwd)
                .login(email, pwd);
        header.clickYourAccount();
        AccountPage profile = new AccountPage(driver);
        assertTrue(profile.isProfileVisible());
    }

    @Test
    public void registrationNegativeTest() {
        pwd = UserConfig.getRandomString(5);
        HeaderItems header = new Header(driver);
        header.clickYourAccount()
                .clickRegistrationButton()
                .register(name, email, pwd);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertTrue(registrationPage.isPwdErrorVisible());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
