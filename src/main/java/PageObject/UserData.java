package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import configure.EnvConfig;

import java.time.Duration;

public class UserData {
    private final WebDriver driver;

    // локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // локатор поля ввода телефона
    private final By userPhone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");

    // локатор поля выбора метро
    private final By userMetro = By.cssSelector("[placeholder='* Станция метро']");

    // локатор поля ввода адреса
    private final By userAddress = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");

    // локатор поля ввода фамилии
    private final By userSurName = By.cssSelector("[placeholder='* Фамилия']");

    // локатор поля ввода имени
    private final By userName = By.cssSelector("[placeholder='* Имя']");


    public UserData (WebDriver driver) {
        this.driver = driver;
    }

    public AboutOrder clickOnButtonNext() {
        driver.findElement(nextButton).click();

        return new AboutOrder(driver);
    }

    public void enterUserPhone(String telephone) {
        driver.findElement(userPhone).sendKeys(telephone);
    }

    public UserData selectMetro(int idStation) {
        driver.findElement(userMetro).click();
        driver.findElement(By.cssSelector("[data-index='" + idStation + "']")).click();

        return this;
    }

    public UserData enterUserAddress(String address) {
        driver.findElement(userAddress).sendKeys(address);

        return this;
    }

    public UserData enterUserSurName(String surName) {
        driver.findElement(userSurName).sendKeys(surName);

        return this;
    }

    public UserData enterUserName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(userName));

        driver.findElement(userName).sendKeys(name);

        return this;
    }
}
