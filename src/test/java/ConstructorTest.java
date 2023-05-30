import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void saucesFocusTest() {
        String focusedElement = new BurgerMainPage(driver)
                .selectSauces()
                .getElementInFocus();
        assertEquals("Соусы", focusedElement);
    }

    @Test
    public void fillingsFocusTest() {
        String focusedElement = new BurgerMainPage(driver)
                .selectFillings()
                .getElementInFocus();
        assertEquals("Начинки", focusedElement);
    }

    @Test
    public void bunsFocusTestByDefault() {
        String focusedElement = new BurgerMainPage(driver)
                .getElementInFocus();
        assertEquals("Булки", focusedElement);
    }

    @Test
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
