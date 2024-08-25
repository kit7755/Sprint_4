import Driver.DriverRule;
import configure.EnvConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import PageObject.MainPage;
import PageObject.SupportFAQ;

// Класс SupportFaqTest выполняет параметризованные тесты для проверки информации в разделе FAQ
@RunWith(Parameterized.class)
public class SupportFaqTest {

    private final int id;
    private final String actual;


    public SupportFaqTest(int id, String actual) {
        this.id = id;
        this.actual = actual;
    }


    @Rule
    public DriverRule driverRule = new DriverRule();

    // Метод, возвращает двумерный массив с вопросами и ответами
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    // Тест для проверки правильности ответа на вопрос
    @Test
    public void validateFaqContent() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();

        // Переход к разделу, клик по вопросу и проверка ответа
        SupportFAQ faq = mainPage.scrollToFaq();
        faq.clickOnQuestion(id)
                .checkAnswer(id, actual);
    }
}
