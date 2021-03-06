import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonPageObject {
    private final WebDriver driver;

    public AmazonPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath="//*[@id=\"searchDropdownBox\"]")
    public WebElement categories;

    @FindBy(xpath="//*[@id=\"twotabsearchtextbox\"]")
    public WebElement input_field;

    @FindBy(xpath="//*[@id=\"nav-search\"]/form/div[2]/div/input")
    public WebElement search;

    @FindBy(xpath="//*[@id=\"priceblock_ourprice\"]")
    public WebElement priceofproduct;

    @FindBy(xpath="//*[@id=\"priceblock_dealprice\"]")
    public WebElement deal_price;

    @FindBy(xpath="//input[@id=\"add-to-cart-button\"]")
    public WebElement addtocart;

    @FindBy(xpath="//*[@id=\"hlb-subcart\"]/div[1]/span/span[2]")
    public WebElement cart_price;

    @FindBy(xpath="//*[@id=\"nav-cart-count-container\"]")
    public WebElement cart;

    @FindBy(xpath="//span[@class=\"currencyINR\"]")
    public WebElement amount;
}
