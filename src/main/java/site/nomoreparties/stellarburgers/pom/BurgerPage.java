package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerPage {
    private final WebDriver driver;
    final static By BURGER_PAGE_HEADER = By.xpath("//h1[text()='Соберите бургер']");
    public BurgerPage(WebDriver driver) {
        this.driver = driver;
    }
}
