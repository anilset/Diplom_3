package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BurgerMainPage {
    private final WebDriver driver;
    private final By BURGER_PAGE_HEADER = By.xpath("//h1[text()='Соберите бургер']");
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    public BurgerMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAccountFromMain(){
        driver.findElement(enterAccountButton).click();
    }



    public Boolean isBurgerPageVisible(){
        List<WebElement> makeBurger = driver.findElements(BURGER_PAGE_HEADER);
        return makeBurger.size() >= 1;
    }
}
