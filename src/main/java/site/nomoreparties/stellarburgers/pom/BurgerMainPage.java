package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BurgerMainPage extends HeaderItems {
    private final WebDriver driver;
    public static final By BURGER_PAGE_HEADER = By.xpath("//h1[text()='Соберите бургер']");
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsButton = By.xpath("//span[text()='Булки']");
    private final By saucesButton = By.xpath("//span[text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[text()='Начинки']");
    private final By focusedConstructorPart = By.xpath("//div[contains(@class,'current')]");

    public BurgerMainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPage enterAccountFromMain(){
        driver.findElement(enterAccountButton).isEnabled();
        driver.findElement(enterAccountButton).click();
        return new LoginPage(driver);
    }



    public Boolean isBurgerPageVisible(){
        List<WebElement> makeBurger = driver.findElements(BURGER_PAGE_HEADER);
        return makeBurger.size() == 1;
    }

    public BurgerMainPage selectBuns(){
        driver.findElement(bunsButton).click();
        return this;
    }

    public BurgerMainPage selectSauces(){
        driver.findElement(saucesButton).click();
        return this;
    }

    public BurgerMainPage selectFillings(){
        driver.findElement(fillingsButton).click();
        return this;
    }

    public String getElementInFocus() {
        String focusedElementText = driver.findElement(focusedConstructorPart).getText();
        return focusedElementText;
    }
}
