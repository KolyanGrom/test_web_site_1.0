import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class webSiteTests {
    private WebDriver driver;


    @BeforeEach
    void setup() {
        driver = new ChromeDriver();

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testWebSiteHappyPath() {

        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input ")).sendKeys("Николай Булкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void testWebSiteBadPath_1() {

        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input ")).sendKeys("Kolya Bylkin");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();
        String text = driver.findElement(By.cssSelector("[class=input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void testWebSiteBadPath_2() {

        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input ")).sendKeys("Николай Булкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input ")).sendKeys("+799988877666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[role=button]")).click();
        WebElement form = driver.findElement(By.cssSelector("[data-test-id=phone]"));
        String text = form.findElement(By.cssSelector("[class=input__sub]")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }
}
