package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.time.Duration;

public class HW15 {
    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_URL");

    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoqaTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }

    @Test
    public void fillFormOnDemoqaTest(){
        //Buttons seçeneğine tıklatıyoruz
        WebElement elementButton= driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[5]"));
        elementButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        WebElement clickmeButton = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/button[1]"));
        clickmeButton.click();
        WebElement messageElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dynamicClickMessage']")));
        String message = messageElement.getText();
        System.out.println(message);
        Assert.assertEquals(message, "You have done a dynamic click");


        driver.get("https://demoqa.com/webtables");

        //Add butonuna tıklanır ve kullanıcı bilgileri girilir
        WebElement btnAdd = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        btnAdd.click();

        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName.sendKeys("Büşra");

        WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
        lastName.sendKeys("Demirbaş");

        WebElement email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        email.sendKeys("demirbas@gmail.com");

        WebElement id= driver.findElement(By.xpath("//input[@id='age']"));
        id.sendKeys("27");

        WebElement salary = driver.findElement(By.xpath("//input[@id='salary']"));
        salary.sendKeys("100000");

        WebElement department = driver.findElement(By.xpath("//input[@id='department']"));
        department.sendKeys("IT");

        WebElement btnSubmit = driver.findElement(By.xpath("//button[@id='submit']"));
        btnSubmit.click();

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0, 500);");

        //Düzenleme butonuna KALEM SEMBOLÜNE tıklandı
        WebElement btnEdit = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div[1]/div[7]"));
        btnEdit.click();

        WebElement firstName2 = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName2.sendKeys("Büşra2");

        WebElement btnSubmit2 = driver.findElement(By.xpath("//button[@id='submit']"));
        btnSubmit2.click();

    }

    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }


}
