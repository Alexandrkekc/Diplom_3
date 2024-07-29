import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.RandomUser;
import ru.yandex.praktikum.StepsForUser;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.driver.WebDriverCreator;
import ru.yandex.praktikum.pom.ConstructorPage;
import ru.yandex.praktikum.pom.ForgotPasswordPage;
import ru.yandex.praktikum.pom.LoginPage;
import ru.yandex.praktikum.pom.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private String name;
    private String email;
    private String password;

    @Before
    public void createNewChromeDriver() {
        driver = WebDriverCreator.createWebDriver();
        RandomUser randomUser = new RandomUser();
        name = randomUser.getName();
        email = randomUser.getEmail();
        password = randomUser.getPassword();

        StepsForUser stepsForUser = new StepsForUser();
        stepsForUser.setUser(new User(email, password, name));
        stepsForUser.createUser();
    }

    @Test
    public void loginUserCheck() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage()
                .clickInputButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        assertEquals("Оформить заказ", constructorPage.checkOrderButtonText());
    }

    @Test
    public void loginUserCheckPersonalAccount() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage()
                .clickPersonalAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        assertEquals("Оформить заказ", constructorPage.checkOrderButtonText());
    }

    @Test
    public void loginUserFromRegistrationPageCheck() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage()
                .clickInputButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        assertEquals("Оформить заказ", constructorPage.checkOrderButtonText());
    }

    @Test
    public void loginUserFromForgotPasswordPageCheck() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage()
                .clickInputButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        assertEquals("Оформить заказ", constructorPage.checkOrderButtonText());
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
