package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    // Кнопка заказа в хедере
    private final By headerOrderButton = By.className("Button_Button__ra12g");

    // Кнопка заказка в теле страницы
    private final By bodyOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    // Блок с вопросами
    private final By questions = By.className("Home_FAQ__3uVm4");

    //текст ответа на вопрос
    private final By visibleAnswer = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Клик по кнопке заказа в хедере
    public void clickOrderInHeader() {
        driver.findElement(headerOrderButton).click();
    }

    // Клик по кнопке заказа в теле
    public void clickOrderInBody() {

        WebElement element = driver.findElement(bodyOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        element.click();
    }

    // Обработка кликов по разным кнопкам
    public void clickOrderButton(String buttonType) {
        if (buttonType.equals("header")) {
            clickOrderInHeader();
        } else {
            clickOrderInBody();
        }
    }

    // Скролл до вопросов
    public void scrollToQuestions() {
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Клик по стрелке с вопросом и получения текста ответа
    public String clickQuestionGetAnswer(String question) {
        scrollToQuestions();

        WebElement element = driver.findElement(By.xpath(".//div[text() = '" + question + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(visibleAnswer));

        return driver.findElement(By.xpath(".//div[text() = '" + question + "']/parent::div/parent::div//p")).getText();
    }
}
