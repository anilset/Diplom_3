package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static site.nomoreparties.stellarburgers.pom.RegistrationPage.REGISTER_PAGE_HEADER;

public class LoginPage {
    private final WebDriver driver;
    private final static By LOGIN_PAGE_HEADER = By.xpath("//h2[text()='Вход']");
    private final static By emailField = By.name("name");
    private final By pwdField = By.name("Пароль");
    private final By enterButton = By.xpath("//*[text()='Войти']");

    final By registrationButton = By.xpath("//a[text()='Зарегистрироваться']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).isEnabled();
        driver.findElement(registrationButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_PAGE_HEADER));
        return new RegistrationPage(driver);
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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new BurgerMainPage(driver);
    }

    public Boolean isLoginPageVisible(){
        List<WebElement> loginHeader = driver.findElements(LOGIN_PAGE_HEADER);
        return loginHeader.size() >= 1;
    }


}
