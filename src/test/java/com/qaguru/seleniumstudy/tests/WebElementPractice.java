package com.qaguru.seleniumstudy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebElementPractice {
    private final String baseUrl = "http://qaguru.ca/webelementapp.php";
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }

    @Test
    public void selectGender() {
        WebElement radMale = driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[1]"));
        Assert.assertTrue(radMale.isSelected(), "Male radio button is not selected by default");
        driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[2]")).click();

    }

    @Test
    public void setTextBox() {
        WebElement webElement = driver.findElement(By.name("firstname"));
        webElement.clear();
        webElement.sendKeys("John");
    }

    @Test
    public void listBox() {
        WebElement webElement = driver.findElement(By.name("cars"));
        Select select = new Select(webElement);
        select.selectByVisibleText("Audi");
        List<WebElement> webElements = select.getOptions();
        System.out.println(webElements);
        for (WebElement webE : webElements) {
            System.out.println(webE.getText());
        }

    }

    @Test
    public void checkBox() throws InterruptedException {
        Thread.sleep(1000);
        WebElement webElement = driver.findElement(By.name("vehicle1"));
        System.out.println(webElement.isSelected());
        webElement.click();

    }

    @Test
    public void calender() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("bday")).sendKeys("2020" + Keys.TAB + "1211");

    }
    @Test
    public void buttonAndAlert() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"home-5\"]/button")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"QA GURU!","Incorrect message");
        alert.accept();

    }
    @Test
    public void implicitWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.name("bday")).sendKeys("2020" + Keys.TAB + "1211");

    }
    @Test
    public void explicitWait(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("bday")));

        webElement.sendKeys("2020" + Keys.TAB + "1211");

    }

}
