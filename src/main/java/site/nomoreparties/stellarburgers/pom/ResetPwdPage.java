package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ResetPwdPage extends HeaderItems{
    private final WebDriver driver;
    private final By enterButton = By.xpath("//*[text()='Войти']");

    public ResetPwdPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    static final By RESET_PWD_HEADER = By.xpath("//h2[text()='Восстановление пароля']");
    public LoginPage clickEnterButton() {
        driver.findElement(enterButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }
}
