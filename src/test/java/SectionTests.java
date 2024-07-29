import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.WebDriverCreator;
import ru.yandex.praktikum.pom.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class SectionTests {

    private WebDriver driver;
    private ConstructorPage constructorPage;

    @Before
    public void createNewChromeDriver() {
        driver = WebDriverCreator.createWebDriver();
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    public void transactionToSauceCheck() {
        constructorPage.openConstructorPage()
                .clickSauceButton();
        assertTrue(constructorPage.checkSauceText());
    }

    @Test
    public void transactionToFillingCheck() {
        constructorPage.openConstructorPage()
                .clickFillingButton();
        assertTrue(constructorPage.checkFillingText());
    }

    @Test
    public void transactionToBunCheck() {
        constructorPage.openConstructorPage()
                .clickFillingButton()
                .clickBunButton();
        assertTrue(constructorPage.checkBunText());
    }

    @After
    public void tearDown() {
        driver.quit();

    }
}
