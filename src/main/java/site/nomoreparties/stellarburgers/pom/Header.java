package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static site.nomoreparties.stellarburgers.pom.LoginPage.LOGIN_PAGE_HEADER;

public class Header implements HeaderItems{
    final WebDriver driver;
    public static final By LOGO = By.xpath("//*[@class='AppHeader_header__logo__2D0X2']");
    final By constructor = By.xpath("//p[text()='Конструктор']");
    final By ordersButton = By.xpath("//p[text()='Лента Заказов']");
    final By accountButton = By.xpath("//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public BurgerPage clickLogo(){
        driver.findElement(LOGO).isEnabled();
        driver.findElement(LOGO).click();
        return new BurgerPage(driver);
    }

    public BurgerPage clickConstructorButton(){
        driver.findElement(constructor).isEnabled();
        driver.findElement(constructor).click();
        return new BurgerPage(driver);
    }

    public LoginPage clickYourAccount(){
        driver.findElement(accountButton).isEnabled();
        driver.findElement(accountButton).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }
}
