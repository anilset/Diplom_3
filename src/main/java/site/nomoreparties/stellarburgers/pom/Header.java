package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Header implements HeaderItems{
    private final WebDriver driver;
    public static final By LOGO = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");
    private final By constructor = By.xpath("//p[text()='Конструктор']");
    private final By ordersButton = By.xpath("//p[text()='Лента Заказов']");
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public BurgerMainPage clickLogo(){
        driver.findElement(LOGO).isEnabled();
        driver.findElement(LOGO).click();
        return new BurgerMainPage(driver);
    }

    public BurgerMainPage clickConstructorButton(){
        driver.findElement(constructor).isEnabled();
        driver.findElement(constructor).click();
        return new BurgerMainPage(driver);
    }

    public LoginPage clickYourAccount(){
        driver.findElement(accountButton).isEnabled();
        driver.findElement(accountButton).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }
}
