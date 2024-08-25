package PageObject;

import configure.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SupportFAQ {
    private final WebDriver driver;

    // локатор в блоке "Вопросы о важном"
    private final String question = "accordion__heading-";

    // локатор ответа в блоке "Вопросы о важном"
    private final String answer = "accordion__panel-";

    public SupportFAQ(WebDriver driver) {
        this.driver = driver;
    }


    public SupportFAQ clickOnQuestion(int id) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(question + id)));
        driver.findElement(By.id(question + id)).click();

        return this;
    }


    public void checkAnswer(int id, String actual) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answer + id)));

        assertEquals(driver.findElement(By.id(answer + id)).getText(), actual);
    }
}