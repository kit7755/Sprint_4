package PageObject;

import configure.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StatusPage {
    private final WebDriver driver;

    // локатор картинки с текстом "Заказ не найден"
    private final By notFound = By.cssSelector("[alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }


    public void checkNotFound() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFound));
    }

}