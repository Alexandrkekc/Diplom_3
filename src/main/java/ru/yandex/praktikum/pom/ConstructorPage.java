package ru.yandex.praktikum.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    private final static String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";

    private final By inputInAccountButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text() = 'Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text() = 'Оформить заказ']");
    private final By personalAccountButton = By.xpath(".//*[text() = 'Личный Кабинет']"); //By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() = 'Личный Кабинет']");
    private final By sauceButton = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Соусы']");
    private final By fillingButton = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Начинки']");
    private final By bunButton = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Булки']");
    private final By sauceChapter = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Соусы']");
    private final By fillingChapter = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Начинки']");
    private final By bunChapter = By.xpath(".//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Булки']");


    @Step("Открыть страницу конструктора")
    public ConstructorPage openConstructorPage() {
        driver.get(MAIN_PAGE);
        driver.manage().window().fullscreen();
        return this;
    }

    @Step("Проверка текста кнопки Оформить заказ")
    public Object checkOrderButtonText() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));

        return textButton.getText();
    }

    @Step("Нажать на кнопку Войти в аккаунт на стартовой странице")
    public ConstructorPage clickInputButton() {
        driver.findElement(inputInAccountButton).click();
        return this;
    }

    @Step("Нажать на кнопку Личный Кабинет на стартовой странице")
    public ConstructorPage clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return this;
    }

    @Step("Нажать на кнопку Соусы")
    public ConstructorPage clickSauceButton() {
        driver.findElement(sauceButton).click();
        return this;
    }

    @Step("Нажать на кнопку Начинки")
    public ConstructorPage clickFillingButton() {
        driver.findElement(fillingButton).click();
        return this;
    }

    @Step("Нажать на кнопку Булки")
    public ConstructorPage clickBunButton() {
        driver.findElement(bunButton).click();
        return this;
    }

    @Step("Проверка отображения текста раздела Соусы")
    public boolean checkSauceText() {
        return driver.findElement(sauceChapter).isDisplayed();
    }

    @Step("Проверка отображения текста раздела Начинки")
    public boolean checkFillingText() {
        return driver.findElement(fillingChapter).isDisplayed();
    }

    @Step("Проверка отображения текста раздела Булки")
    public boolean checkBunText() {
        return driver.findElement(bunChapter).isDisplayed();
    }
}