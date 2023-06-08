package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationPage extends HeaderItems {
   private final WebDriver driver;
   final static By REGISTER_PAGE_HEADER = By.xpath("//h2[text()='Регистрация']");
   private final By nameField = By.xpath ("//fieldset[1]/div[1]/div[1]/input[1]");
   private final By emailField = By.xpath("//fieldset[2]/div[1]/div[1]/input[1]");
   private final By pwdField = By.xpath("//fieldset[3]/div[1]/div[1]/input[1]");
   private final By pwdError = By.xpath("//*[text()='Некорректный пароль']");
   private final  By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By enterButton = By.xpath("//*[text()='Войти']");
   public RegistrationPage(WebDriver driver) {
       super(driver);
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }

    public LoginPage register(String name, String email, String pwd){
        inputName(name);
        inputEmail(email);
        inputPwd(pwd);
        clickRegisterButton();
        return new LoginPage(driver);
    }

    public LoginPage clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }

    public Boolean isPwdErrorVisible(){
        List<WebElement> error = driver.findElements(pwdError);
        return error.size() == 1;
    }

}
