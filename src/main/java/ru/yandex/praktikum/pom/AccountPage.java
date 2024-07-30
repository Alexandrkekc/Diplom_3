package ru.yandex.praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By logoutButton = By.className("Account_button__14Yp3");
    private final By constructorButton = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() = 'Конструктор']");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");


    @Step("Проверить текст кнопки выхода")
    public Object checkLogoutButtonText() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return textButton.getText();
    }

    @Step("Кликнуть по кнопке конструктора")
    public AccountPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    @Step("Кликнуть по лого")
    public AccountPage clickLogoButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(logoButton).click();
        return this;
    }

    @Step("Кликнуть по кнопке выхода")
    public AccountPage clickLogoutButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(logoutButton).click();
        return this;
    }
}