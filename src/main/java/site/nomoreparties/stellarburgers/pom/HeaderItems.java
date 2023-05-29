package site.nomoreparties.stellarburgers.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public interface HeaderItems {
    BurgerPage clickLogo();
   BurgerPage clickConstructorButton();
   LoginPage clickYourAccount();

}
