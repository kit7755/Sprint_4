import Driver.DriverRule;
import configure.EnvConfig;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;
import PageObject.YandexPage;


public class LogoClickTest {
    // Текст, который будет вводиться на странице Yandex
    String text = "Hi!";


    @Rule
    public DriverRule driverRule = new DriverRule();

    // Тест для проверки перенаправления на страницу Yandex по клику на логотип
    @Test
    public void openYandexPage() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();


        YandexPage ya = mainPage.clickOnYandex();

        ya.openInNewTab()
                .enterText(text);
    }

    // Тест для проверки перенаправления на страницу Samokat по клику на логотип
    @Test
    public void openSamokatPage() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open()
                .clickOnSamokat()
                .checkRedirectToSamokat();
    }
}

