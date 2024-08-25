import Driver.DriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import PageObject.AboutOrder;
import PageObject.MainPage;
import PageObject.UserData;
import configure.EnvConfig;

// Класс ValidOrderTest выполняет параметризованные тесты для создания действительных заказов
@RunWith(Parameterized.class)
public class ValidOrderTest {

    // Поля для хранения данных, необходимых для создания заказа
    private final String name;
    private final String surName;
    private final String address;
    private final int idMetro;
    private final String telephone;
    private final String date;
    private final String period;
    private final String comment;
    private final String color;

    // Конструктор класса, который инициализирует поля
    public ValidOrderTest(String name, String surName, String address, int idStation, String telephone, String date, String period, String color, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.idMetro = idStation;
        this.telephone = telephone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }


    @Rule
    public DriverRule driverRule = new DriverRule();

    // Параметризированный метод, предоставляющий данные для теста. Возвращает двумерный массив с данными заказа
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Гарри", "Поттер", "Поттерович", 1, "63241236974",  "06.05.2024", "сутки", "black", "Оставить у подъезда"},
                {"Гермиона", "Грейнджер", " ", 10, "62358942369", "07.06.2024", "пять дней", "grey", "Передать по пути в библиотеку."},
                {"Рон", "Уизли", "Уизлович", 5, "5555566662", "10.06.2024", "две недели", "black", "Оставить у замка."},
                {"Драко", "Малфой", "Малфойвич", 15, "34860568941", "15.06.2024", "неделя", "grey", "У главного входа."},
        };
    }
    // Тест для создания заказа через кнопку в верху страницы
    @Test
    public void createValidOrderByUpButton() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();

        // Заполнение данных заказа и проверка его создания
        UserData userInformation = mainPage.clickOnOrderButtonUpHeader();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetro(idMetro)
                .enterUserPhone(telephone);

        AboutOrder aboutOrder = userInformation.clickOnButtonNext();
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();
    }

    // Тест для создания действительного заказа в внизу
    @Test
    public void createValidOrderByDownButton() {
        WebDriver driver = driverRule.getDriver();


        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .addCookie(EnvConfig.COOKIE_NAME_1, EnvConfig.COOKIE_VALUE)
                .addCookie(EnvConfig.COOKIE_NAME_2, EnvConfig.COOKIE_NAME_2)
                .open();

        // Заполнение данных заказа и проверка его создания
        UserData userInformation = mainPage.clickOnDownOrderButton();
        userInformation.enterUserName(name)
                .enterUserSurName(surName)
                .enterUserAddress(address)
                .selectMetro(idMetro)
                .enterUserPhone(telephone);

        AboutOrder aboutOrder = userInformation.clickOnButtonNext(); // Переход к следующему этапу заказа
        aboutOrder.enterDate(date)
                .selectOrderPeriod(period)
                .selectColor(color)
                .enterComment(comment)
                .clickToContinueOrder()
                .confirmOrder()
                .checkTheOrderCreation();
    }
}
