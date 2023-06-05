package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static site.nomoreparties.stellarburgers.pom.BurgerMainPage.BURGER_PAGE_HEADER;
import static site.nomoreparties.stellarburgers.pom.RegistrationPage.REGISTER_PAGE_HEADER;
import static site.nomoreparties.stellarburgers.pom.ResetPwdPage.RESET_PWD_HEADER;

public class LoginPage extends HeaderItems {
    private final WebDriver driver;
    private final static By LOGIN_PAGE_HEADER = By.xpath("//h2[text()='Вход']");
    private final static By emailField = By.name("name");
    private final By pwdField = By.name("Пароль");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    private final By resetPwdButton = By.xpath("//a[text()='Восстановить пароль']");

    final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).isEnabled();
        driver.findElement(registrationButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_PAGE_HEADER));
        return new RegistrationPage(driver);
    }

    public ResetPwdPage clickResetPwdButton() {
        driver.findElement(resetPwdButton).isEnabled();
        driver.findElement(resetPwdButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(RESET_PWD_HEADER));
        return new ResetPwdPage(driver);
    }

    public LoginPage inputEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return this;
    }
    public LoginPage inputPwd(String pwd){
        driver.findElement(pwdField).clear();
        driver.findElement(pwdField).sendKeys(pwd);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return this;
    }

    public BurgerMainPage clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new BurgerMainPage(driver);
    }

    public BurgerMainPage login(String email, String pwd) {
        inputEmail(email);
        inputPwd(pwd);
        clickEnterButton();
        new WebDriverWait(driver, Duration.ofSeconds(5000)).until((webDriver -> driver.findElement(BURGER_PAGE_HEADER).getText() != null));
        return new BurgerMainPage(driver);
    }

    public Boolean isLoginPageVisible(){
        List<WebElement> loginHeader = driver.findElements(LOGIN_PAGE_HEADER);
        return loginHeader.size() == 1;
    }


}
