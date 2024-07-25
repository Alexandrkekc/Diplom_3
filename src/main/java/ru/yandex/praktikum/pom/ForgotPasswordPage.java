package ru.yandex.praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    private final static String FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By enterButton = By.xpath(".//a[text() = 'Войти']");

    @Step("Открыть страницу восстановления пароля")
    public ForgotPasswordPage openForgotPasswordPage() {
        driver.get(FORGOT_PASSWORD_PAGE);
        driver.manage().window().fullscreen();
        return this;
    }

    @Step("Нажать на кнопку Войти")
    public ForgotPasswordPage clickInputButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}