import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.RandomUser;
import ru.yandex.praktikum.StepsForUser;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.driver.WebDriverCreator;
import ru.yandex.praktikum.pom.AccountPage;
import ru.yandex.praktikum.pom.ConstructorPage;
import ru.yandex.praktikum.pom.LoginPage;

import static org.junit.Assert.assertEquals;

public class LogoutTest {

    WebDriverCreator webDriverCreator = new WebDriverCreator();
    private WebDriver driver;
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

        StepsForUser stepsForUser = new StepsForUser();
        stepsForUser.setUser(new User(email, password, name));
        stepsForUser.createUser();
    }


    @Test
    public void logOutCheck() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .loginUser(email, password);
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickPersonalAccountButton();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutButton();
        assertEquals("Войти", loginPage.checkLoginButtonText());
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
