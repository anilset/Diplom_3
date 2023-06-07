package site.nomoreparties.stellarburgers.tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.config.UserConfig;
import site.nomoreparties.stellarburgers.extensions.WevDriverFactory;
import site.nomoreparties.stellarburgers.pom.AccountPage;
import site.nomoreparties.stellarburgers.pom.BurgerMainPage;
import site.nomoreparties.stellarburgers.pom.HeaderItems;

import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static site.nomoreparties.stellarburgers.config.UserConfig.BASE_URI;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginAccountTest {
    private WebDriver driver;
    private static String login;
    private static String pwd;
    private static String name;
    private static ValidatableResponse createUser;
    private static String accessToken;
    @BeforeAll
    public void createUser() {
        baseURI = BASE_URI;
        login = UserConfig.getRandomLogin();
        pwd = UserConfig.getRandomPwd();
        name = UserConfig.getRandomString(9);
        createUser = UserConfig.createUser(login, pwd, name);
        accessToken = UserConfig.getAccessToken(createUser);
    }

    @BeforeEach
    public void setUp() {
        driver = WevDriverFactory.getDriver();
    }

    @Test
    @DisplayName("Проверка логина с главной страницы")
    public void loginFromHomePageTest(){
        Boolean isLoggedIn = new BurgerMainPage(driver)
                .enterAccountFromMain()
                .login(login, pwd)
                .isBurgerPageVisible();
        assertTrue(isLoggedIn);
    }

    @Test
    @DisplayName("Проверка логина из личного кабинета")
    public void loginFromAccountPage(){
        Boolean isLoggedIn = new HeaderItems(driver)
                .clickYourAccount()
                .login(login, pwd)
                .isBurgerPageVisible();
        assertTrue(isLoggedIn);
    }

    @Test
    @DisplayName("Проверка логина через кнопку Войти Формы регистрации")
    public void loginFromRegistrationPage(){
        Boolean isLoggedIn = new HeaderItems(driver)
                .clickYourAccount()
                .clickRegistrationButton()
                .clickEnterButton()
                .login(login, pwd)
                .isBurgerPageVisible();
        assertTrue(isLoggedIn);
    }

    @Test
    @DisplayName("Проверка логина через кнопку Войти Формы восстановления пароля")
    public void loginFromPwdResetPage(){
        Boolean isLoggedIn = new HeaderItems(driver)
                .clickYourAccount()
                .clickResetPwdButton()
                .clickEnterButton()
                .login(login, pwd)
                .isBurgerPageVisible();
        assertTrue(isLoggedIn);
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет")
    public void accountPageTransferTest() {
        new HeaderItems(driver)
                .clickYourAccount()
                .login(login, pwd)
                .clickYourAccount();
        assertTrue(new AccountPage(driver).isProfileVisible());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта в личном кабинете")
    public void logOutTest() {
        new HeaderItems(driver)
                .clickYourAccount()
                .login(login, pwd)
                .clickYourAccount();
        Boolean isLoggedOut = new AccountPage(driver)
                .clickLogoutButton()
                .isLoginPageVisible();
        assertTrue(isLoggedOut);
    }

    @Test
    @DisplayName("Проверка перехода на страницу конструктора по клику на лого из личного кабинета")
    public void accountPageLogoClickTest() {
        Boolean isTransferredToMain = new HeaderItems(driver)
                .clickYourAccount()
                .clickLogo()
                .isBurgerPageVisible();
        assertTrue(isTransferredToMain);
    }

    @Test
    @DisplayName("Проверка перехода на страницу конструктора по клику на конструктор из личного кабинета")
    public void accountPageConstructorClickTest() {
        Boolean isTransferredToMain = new HeaderItems(driver)
                .clickYourAccount()
                .clickConstructorButton()
                .isBurgerPageVisible();
        assertTrue(isTransferredToMain);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @AfterAll
    public void deleteTestUser() {
        ValidatableResponse loginByAPI = UserConfig.login(login, pwd);
        accessToken = UserConfig.getAccessToken(loginByAPI);
        UserConfig.deleteUser(accessToken);
    }
}
