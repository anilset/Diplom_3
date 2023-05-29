package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage {
    private final WebDriver driver;
    private final By PROFILE_HEADER = By.xpath("//a[text()='Профиль']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isProfileVisible(){
        List<WebElement> profile = driver.findElements(PROFILE_HEADER);
        return profile.size() >= 1;
    }

}
