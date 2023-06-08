package site.nomoreparties.stellarburgers.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.extensions.WevDriverFactory;
import site.nomoreparties.stellarburgers.pom.BurgerMainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = WevDriverFactory.getDriver();
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
