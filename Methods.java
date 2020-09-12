import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Methods {

    Properties prop = new Properties();
    AmazonPageObject page1;

    public void selectProduct(WebDriver driver, String prodname, String suggest, String filepath) throws IOException, InterruptedException {
        FileInputStream input = new FileInputStream("C:\\Users\\Rani Marimuthu\\IdeaProjects\\Test1\\src\\main\\resources\\amazonproperty.properties");
        prop.load(input);
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        int count = 0;
        int lastchar=0;
        System.out.println("lastchar: "+lastchar);
        page1 = PageFactory.initElements(driver, AmazonPageObject.class);
        for (int i = 0; i < prodname.length(); i++) {
            lastchar++;
            String a = String.valueOf(prodname.charAt(i));
            page1.input_field.sendKeys(a);
            Thread.sleep(3000);
            List<WebElement> list1;
            list1 = driver.findElements(By.xpath(suggest));
            System.out.println("Size of list: " + list1.size());
            FileOutputStream output = new FileOutputStream(filepath);
            for (int q = 0; q < list1.size(); q++) {
                byte[] temp = list1.get(q).getText().getBytes();
                output.write(temp);
                }
            for (int j = 0; j < list1.size(); j++) {
                System.out.println("Items:" + list1.get(j).getText());
                if (list1.get(j).getText().equalsIgnoreCase(prodname)) {
                    count = j;
                    list1.get(j).click();
                }
            }
            if(lastchar == prodname.length() && count ==0)
            {
                wait.until((ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("search")))));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", page1.search);
            }
        }
    }
}
