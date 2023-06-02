package site.nomoreparties.stellarburgers.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.pom.BurgerMainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static site.nomoreparties.stellarburgers.config.UserConfig.URL;
import static site.nomoreparties.stellarburgers.pom.HeaderItems.LOGO;

public class ConstructorTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGO));
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка прехода в раздел Конструктора Соусы ")
    public void saucesFocusTest() {
        String focusedElement = new BurgerMainPage(driver)
                .selectSauces()
                .getElementInFocus();
        assertEquals("Соусы", focusedElement);
    }

    @Test
    @DisplayName("Проверка прехода в раздел Конструктора Начинки")
    public void fillingsFocusTest() {
        String focusedElement = new BurgerMainPage(driver)
                .selectFillings()
                .getElementInFocus();
        assertEquals("Начинки", focusedElement);
    }

    @Test
    @DisplayName("Проверка, что при открытии главной страницы раздел Конструктора Булки активен")
    public void bunsFocusTestByDefault() {
        String focusedElement = new BurgerMainPage(driver)
                .getElementInFocus();
        assertEquals("Булки", focusedElement);
    }

    @Test
    @DisplayName("Проверка прехода в раздел Конструктора Булки")
    public void bunsFocusTest() {
        String focusedElement = new BurgerMainPage(driver)
                .selectFillings()
                .selectSauces()
                .selectBuns()
                .getElementInFocus();
        assertEquals("Булки", focusedElement);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
