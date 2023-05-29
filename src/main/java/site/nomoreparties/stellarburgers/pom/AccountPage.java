package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage {
    final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    final static By PROFILE_HEADER = By.xpath("//a[text()='Профиль']");
    public Boolean isProfileVisible(){
        List<WebElement> profile = driver.findElements(PROFILE_HEADER);
        return profile.size() >= 1;
    }

}
