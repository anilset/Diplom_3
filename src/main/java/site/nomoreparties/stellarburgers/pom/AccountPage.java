package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AccountPage {
    private final WebDriver driver;
    private final By PROFILE_HEADER = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isProfileVisible(){
        List<WebElement> profile = driver.findElements(PROFILE_HEADER);
        return profile.size() >= 1;
    }

    public LoginPage clickLogoutButton() {
        driver.findElement(logoutButton).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }
}
