import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;
    private final String buttonType;
    private final String username;
    private final String userSurname;
    private final String rentDate;
    private final String comment;
    private  final String color;

    public OrderPageTest(String buttonType, String username, String userSurname, String rentDate, String comment, String color){
        this.buttonType = buttonType;
        this.username = username;
        this.userSurname = userSurname;
        this.rentDate = rentDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData(){
        return new Object[][]{
                {"body", "Райан", "Гослинг", "21.09.2023", "Текст комментария", "Черный"},
                {"header", "Иван", "Петров", "31.08.2023", "Комментарий!" , "Серый"},
        };
    }

    @Test
    public void scooterOrderChromeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickOrderButton(buttonType);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.enterPersonalData(username, userSurname, "Тверская,1", "79990001234");
        objOrderPage.enterRentData(rentDate, color, comment);
        objOrderPage.confirmOrder();
        Assert.assertEquals("Не удалось оформить заказ",true, objOrderPage.getSuccessTitle());
    }

    @Test
    public void scooterOrderFirefoxTest() {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.33.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickOrderButton(buttonType);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.enterPersonalData(username, userSurname, "Тверская,1", "79990001234");
        objOrderPage.enterRentData(rentDate, color, comment);
        objOrderPage.confirmOrder();
        Assert.assertEquals("Не удалось оформить заказ",true, objOrderPage.getSuccessTitle());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
