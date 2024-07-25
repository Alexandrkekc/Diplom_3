import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.RandomUser;
import ru.yandex.praktikum.StepsForUser;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.driver.WebDriverCreator;
import ru.yandex.praktikum.pom.LoginPage;
import ru.yandex.praktikum.pom.RegistrationPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {

    WebDriverCreator webDriverCreator = new WebDriverCreator();
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private String name;
    private String email;
    private String password;

    @Before
    public void createNewChromeDriver() {
        driver = webDriverCreator.createWebDriver();
        RandomUser randomUser = new RandomUser();
        name = randomUser.getName();
        email = randomUser.getEmail();
        password = randomUser.getPassword();
        registrationPage = new RegistrationPage(driver);
    }


    @Test
    public void registrationCheck() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        registrationPage.openRegistrationPage()
                .userRegistration(name, email, password);
        LoginPage loginPage = new LoginPage(driver);
        assertEquals("Войти", loginPage.checkLoginButtonText());
    }

    @Test
    public void registrationCheckWithShortPassword() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        registrationPage.openRegistrationPage()
                .userRegistrationWithShortPassword(name, email, "123");
        assertEquals("Некорректный пароль", registrationPage.checkErrorText());
    }

    @After
    public void tearDown() {
        driver.quit();

        User user = new User(email, password);
        StepsForUser stepsForUser = new StepsForUser();
        String accessToken = stepsForUser.loginUser(user).then().extract().path("accessToken");
        if (accessToken != null) {
            stepsForUser.deleteUser(accessToken);
        }

    }
}