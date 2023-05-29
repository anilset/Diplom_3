package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static site.nomoreparties.stellarburgers.pom.LoginPage.LOGIN_PAGE_HEADER;

public class RegistrationPage {
    final WebDriver driver;
    final static By REGISTER_PAGE_HEADER = By.xpath("//h2[text()='Регистрация']");
    final By nameField = By.xpath ("//fieldset[1]/div[1]/div[1]/input[1]");
    final By emailField = By.xpath("//fieldset[2]/div[1]/div[1]/input[1]");
    final By pwdField = By.xpath("//fieldset[3]/div[1]/div[1]/input[1]");
    final By pwdError = By.xpath("//*[contains(@class,'error')]");
    final  By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegistrationPage inputName(String name){
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public RegistrationPage inputEmail(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        return this;
    }
    public RegistrationPage inputPwd(String pwd){
        driver.findElement(pwdField).clear();
        driver.findElement(pwdField).sendKeys(pwd);
        return this;
    }

    public LoginPage clickRegisterButton(){
        driver.findElement(registerButton).isEnabled();
        driver.findElement(registerButton).click();
        /*new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PAGE_HEADER));*/
        return new LoginPage(driver);
    }

    public LoginPage register(String name, String email, String pwd){
        inputName(name);
        inputEmail(email);
        inputPwd(pwd);
        clickRegisterButton();
        return new LoginPage(driver);
    }

    public Boolean isPwdErrorVisible(){
        List<WebElement> error = driver.findElements(pwdError);
        return error.size() >= 1;
    }

}
