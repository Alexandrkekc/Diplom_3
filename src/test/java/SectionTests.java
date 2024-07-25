import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.RandomUser;
import ru.yandex.praktikum.StepsForUser;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.driver.WebDriverCreator;
import ru.yandex.praktikum.pom.ConstructorPage;

import static org.junit.Assert.assertEquals;

public class SectionTests {

    WebDriverCreator webDriverCreator = new WebDriverCreator();
    private WebDriver driver;
    private ConstructorPage constructorPage;

    @Before
    public void createNewChromeDriver() {
        driver = webDriverCreator.createWebDriver();
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    public void transactionToSauceCheck() {
        constructorPage.openConstructorPage();
        assertEquals("Соусы", constructorPage.checkSauceText());
    }

    @Test
    public void transactionToFillingCheck() {
        constructorPage.openConstructorPage();
        assertEquals("Начинки", constructorPage.checkFillingText());
    }

    @Test
    public void transactionToBunCheck() {
        constructorPage.openConstructorPage()
                .clickFillingButton()
                .clickBunButton();
        assertEquals("Булки", constructorPage.checkBunText());
    }


    @After
    public void tearDown() {
        driver.quit();

    }
}
