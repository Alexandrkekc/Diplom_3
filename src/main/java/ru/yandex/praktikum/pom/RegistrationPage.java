package ru.yandex.praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    private final static String PAGE_REGISTRATION = "https://stellarburgers.nomoreparties.site/register";

    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private final By regButton = By.xpath(".//*[text() = 'Зарегистрироваться']");
    private final By enterButton = By.xpath(".//a[text() = 'Войти']");
    private final By errorText = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу регистрации")
    public RegistrationPage openRegistrationPage() {
        driver.get(PAGE_REGISTRATION);
        driver.manage().window().fullscreen();
        return this;
    }

    @Step("Зарегистрировать пользователя")
    public RegistrationPage userRegistration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(regButton).click();
        return this;
    }

    @Step("Зарегистрировать пользователя")
    public RegistrationPage userRegistrationWithShortPassword(String name, String email, String shortPassword) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(shortPassword);
        driver.findElement(regButton).click();
        return this;
    }

    @Step("Проверка текста ошибки")
    public Object checkErrorText() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(errorText));

        return driver.findElement(errorText).getText();
    }

    @Step("Нажать на кнопку Войти")
    public RegistrationPage clickInputButton() {
        driver.findElement(enterButton).click();
        return this;
    }

}