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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
        AmazonPageObject page1;
        page1 = PageFactory.initElements(driver, AmazonPageObject.class);
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        Select category = new Select(page1.categories);
        category.selectByVisibleText("Deals");
        meth.selectProduct(driver, prop.getProperty("productname"), prop.getProperty("suggestion"), prop.getProperty("sugesttionlistfile"));
        Thread.sleep(3000);
        List<WebElement> list2;
        list2 = driver.findElements(By.xpath(prop.getProperty("items")));
        System.out.println("Count: " + list2.size());
        list2.get(1).click();
        wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("price")))));
        String price = page1.priceofproduct.getText();
        System.out.println("Price of product: "+price);
//                String prod = list2.get(1).getAttribute("alt");
//                List<WebElement> list3;
//                list3 = driver.findElements(By.xpath(prop.getProperty("price")));
//                System.out.println(list3.get(0).getText());
//                System.out.println("item name:"+prod);
//                list2.get(1).click();
//                Thread.sleep(5000);
//                Set<String> windowids = driver.getWindowHandles();
//                Iterator<String> ids =windowids.iterator();
//                System.out.println("Size: "+windowids.size());
//                while(ids.hasNext()){
//                    //System.out.println("Window ids: "+ids.next().toString());
//                    driver.switchTo().window(ids.next());
//                    //Thread.sleep(3000);
//                    if(driver.getTitle().equals(prod)) {
//                        JavascriptExecutor js = (JavascriptExecutor) driver;
//                        WebDriverWait wait = new WebDriverWait(driver, 3000);
//                        wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("add")))));
//                        js.executeScript("arguments[0].click();", page1.addtocart);
//                        page1.cart.click();
//                        Assert.assertEquals(page1.amount.getText(),"");
//                        return;
//                    }

            }

        }



