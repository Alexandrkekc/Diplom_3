package ru.yandex.praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final static String PAGE_LOGIN = "https://stellarburgers.nomoreparties.site/login";

    private final By emailField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @name = 'name']");
    private final By passwordField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @name = 'Пароль']");
    private final By inputButton = By.xpath(".//button[text() = 'Войти']");

    @Step("Проверить текст кнопки выхода")
    public LoginPage openLoginPage() {
        driver.get(PAGE_LOGIN);
        driver.manage().window().fullscreen();
        return this;
    }

    @Step("Проверка текста кнопки Войти")
    public Object checkLoginButtonText() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(inputButton));

        return driver.findElement(inputButton).getText();
    }

    @Step("Вход пользователя")
    public LoginPage loginUser(String email, String password) {
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ignored){
        }

        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputButton));
        driver.findElement(inputButton).click();
        return this;
    }
}