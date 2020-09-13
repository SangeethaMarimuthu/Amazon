import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.time.Clock;
import java.util.*;

public class SampleAmazonTest {

    Properties prop = new Properties();
    Methods meth = new Methods();

    @Test
    public void addProductToCart() throws IOException, InterruptedException {

        FileInputStream input = new FileInputStream("C:\\Users\\Rani Marimuthu\\IdeaProjects\\Test1\\src\\main\\resources\\amazonproperty.properties");
        prop.load(input);
        System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
        WebDriver driver = new ChromeDriver();
        System.out.println("Hello World");
        String price=null;
        AmazonPageObject page1;
        page1 = PageFactory.initElements(driver, AmazonPageObject.class);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        Select category = new Select(page1.categories);
        category.selectByVisibleText("Deals");
        meth.selectProduct(driver, prop.getProperty("productname"), prop.getProperty("suggestion"), prop.getProperty("sugesttionlistfile"));
        wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("items")))));
        ArrayList<WebElement> list2 = (ArrayList<WebElement>) driver.findElements(By.xpath(prop.getProperty("items")));
        System.out.println("Product one: " + list2.get(0));
        list2.get(0).click();
        Thread.sleep(3000);
        String parent=driver.getWindowHandle();
        System.out.println("Parent window: "+parent);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));
        System.out.println("Titlleee: "+driver.getTitle());
        Thread.sleep(3000);

        System.out.println("price list: "+driver.findElements(By.xpath("//*[@id=\"price\"]/table/tbody/tr")).size());
        try{
            if(page1.deal_price.getSize()!= null)
            {
                price = page1.deal_price.getText();
            }
            else {
                price = page1.priceofproduct.getText();
            }
        }
        catch(Exception e)
        {
            price = page1.priceofproduct.getText();
        }
        System.out.println("Price of product: "+price);
        wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("add_to_cart")))));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", page1.addtocart);
        wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("cartprice")))));
        String cart_price = page1.cart_price.getText();
        Assert.assertEquals(cart_price, price);
    }
}