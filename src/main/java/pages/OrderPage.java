package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final WebDriver driver;
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка Далее
    private final By proceedButton = By.xpath(".//button[text()='Далее']");

    // поле "Когда привезти самокат"
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // поле "Срок аренды"
    private final By rentPeriod = By.className("Dropdown-placeholder");
    //private By rentOneDay = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");

    // чекбоксы цветов
    private final By checkboxBlack = By.id("black");
    private final By checkboxGrey = By.id("grey");

    //поле "Комментарий для курьера"
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка "Заказать"
    private final By order = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");

    // Кнопка "Да" в диалоге подтверждения
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");

    // Сообщение об успешном заказе
    private final By orderSuccess = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввод имени
    public void setUsername(String inputName) {
        driver.findElement(name).sendKeys(inputName);
    }

    // Ввод фамилии
    public void setUserSurname(String inputSurname) {
        driver.findElement(surname).sendKeys(inputSurname);
    }

    // Ввод адреса
    public void setAddress(String inputAddress) {
        driver.findElement(address).sendKeys(inputAddress);
    }

    // Выбор метро
    public void setMetro() {
        driver.findElement(metro).click();
        driver.findElement(By.className("select-search__select")).findElement(By.xpath("./ul/li[2]")).click();
    }

    // Ввод адреса
    public void setPhone(String inputPhone) {
        driver.findElement(phone).sendKeys(inputPhone);
    }

    public void clickProceedButton() {
        driver.findElement(proceedButton).click();
    }

    // Ввод всех данных на первом этапе заказа
    public void enterPersonalData(String name, String surname, String address, String phone) {
        setUsername(name);
        setUserSurname(surname);
        setAddress(address);
        setMetro();
        setPhone(phone);
        clickProceedButton();
    }

    // Когда привезти самокат
    public void setRentDate(String rentDate) {
        driver.findElement(date).sendKeys(rentDate);
        driver.findElement(date).sendKeys(Keys.ENTER);
    }

    // Срок аренды
    public void setRentPeriod() {
        driver.findElement(rentPeriod).click();
        driver.findElement(By.xpath(".//div[text()='сутки']")).click();
    }

    // Выбор цвета
    public void setColor(String color) {
        if (color.equals("Черный")) {
            driver.findElement(checkboxBlack).click();
        } else {
            driver.findElement(checkboxGrey).click();
        }
    }

    // Комментарий для курьера
    public void setComment(String userComment) {
        driver.findElement(comment).sendKeys(userComment);
    }

    //Нажимаем "Заказать"
    public void clickOrder() {
        driver.findElement(order).click();
    }

    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }

    //Заполнение формы аренды самоката
    public void enterRentData(String date, String color, String comment) {
        setRentDate(date);
        setRentPeriod();
        setColor(color);
        setComment(comment);
        clickOrder();
    }

    // Проверяем наличие сообщение "Заказ оформлен"
    public boolean getSuccessTitle() {
        int count = driver.findElements(orderSuccess).size();
        return count > 0;
    }
}
