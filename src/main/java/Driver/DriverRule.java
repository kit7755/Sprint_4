package Driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverRule extends ExternalResource {
    private WebDriver driver;

    // Метод, вызываемый перед каждым тестом для инициализации WebDriver
    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    // Метод, вызываемый после каждого теста для завершения работы WebDriver и закрытия браузера
    @Override
    protected void after() {
        driver.quit();
    }

    // Метод для инициализации WebDriver в зависимости от значения системного свойства "browser"
    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            initFirefox(); // Инициализирует FirefoxDriver, если свойство "browser" равно "firefox"
        } else {
            initChrome(); // Инициализирует ChromeDriver, если свойство "browser" не равно "firefox"
        }
    }

    // Метод для настройки и инициализации FirefoxDriver
    private void initFirefox() {
        WebDriverManager.firefoxdriver().setup(); // Настраивает WebDriver для Firefox
        driver = new FirefoxDriver(); // Создает экземпляр FirefoxDriver
    }

    // Метод для настройки и инициализации ChromeDriver
    private void initChrome() {
        WebDriverManager.chromedriver().setup(); // Настраивает WebDriver для Chrome
        driver = new ChromeDriver(); // Создает экземпляр ChromeDriver
    }

    // Метод для получения текущего экземпляра WebDriver
    public WebDriver getDriver() {
        return driver;
    }
}
