import Driver.DriverRule;
import configure.EnvConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;

// Класс InvalidOrderCheck выполняет параметризованный тест для проверки недействительных номеров заказов
@RunWith(Parameterized.class)
public class InvalidOrderCheck  {
    private final String orderNumber;

    // Конструктор класса, который принимает номер заказа и присваивает его переменной orderNumber
    public InvalidOrderCheck (String orderNumber) {
        this.orderNumber = orderNumber;
    }


    @Rule
    public DriverRule driverRule = new DriverRule();


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"568"},
                {"726"},
                {"321"}
        };
    }

    // Основной тест, который проверяет недействительность номера заказа
    @Test
    public void validateInvalidOrder() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open()
                .clickOnStatusButton()
                .enterOrderNumber(orderNumber)
                .clickOnGo()
                .checkNotFound();
    }
}