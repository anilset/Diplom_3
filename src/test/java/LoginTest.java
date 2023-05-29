import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.UserConfig;
import site.nomoreparties.stellarburgers.extensions.WevDriverFactory;

import static site.nomoreparties.stellarburgers.config.UserConfig.BASE_URI;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private WebDriver driver;
    private static String login;
    private static String pwd;
    private static String name;
    private static ValidatableResponse createUser;
    private static String accessToken;
    @BeforeAll
    public void createUser() {
        RestAssured.baseURI = BASE_URI;
        login = UserConfig.getRandomLogin();
        pwd = UserConfig.getRandomPwd();
        name = UserConfig.getRandomString(3);
        createUser = UserConfig.createUser(login, pwd, name);
        accessToken = UserConfig.getAccessToken(createUser);
    }

    @BeforeEach
    public void setUp() {
        driver = WevDriverFactory.getDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }



    @AfterAll
    public void deleteTestUser() {
            UserConfig.deleteUser(accessToken);
    }
}
