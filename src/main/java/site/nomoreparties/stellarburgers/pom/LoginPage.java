package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static site.nomoreparties.stellarburgers.pom.BurgerPage.BURGER_PAGE_HEADER;
import static site.nomoreparties.stellarburgers.pom.RegistrationPage.REGISTER_PAGE_HEADER;

public class LoginPage {
    final WebDriver driver;
    final static By LOGIN_PAGE_HEADER = By.xpath("//h2[text()='Вход']");
    final static By emailField = By.name("name");
    final By pwdField = By.name("Пароль");
    final By enterButton = By.xpath("//*[text()='Войти']");

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
        return this;
    }

    public BurgerPage clickEnterButton() {
        driver.findElement(enterButton).isEnabled();
        driver.findElement(enterButton).click();
        /*new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_PAGE_HEADER));*/
        return new BurgerPage(driver);
    }

    public BurgerPage login(String email, String pwd) {
        inputEmail(email);
        inputPwd(pwd);
        clickEnterButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_PAGE_HEADER));
        return new BurgerPage(driver);
    }

    public WebElement getEmailField() {
        WebElement emailInputField = driver.findElement(emailField);
        return emailInputField;
    }
    /* public Boolean isLoginPageVisible(){

    }*/


}
