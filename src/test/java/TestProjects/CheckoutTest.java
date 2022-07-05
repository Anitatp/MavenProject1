package TestProjects;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.MainPage;
import resources.Initialization;

public class CheckoutTest extends Initialization {
	public static Logger log = LogManager.getLogger(Initialization.class.getName());
	public WebDriver driver;
	public MainPage mp;
	public CheckoutPage cp;

	@BeforeTest

	public void navigateToPage() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

	}

	@Test

	public void addToCart() {
		List<String> itemsNeeded = Arrays.asList("Cucumber", "Brocolli", "Beetroot");
		mp = new MainPage(driver);
		List<String> siteShopList = mp.getShopList();
		List<WebElement> adder = mp.addItem();
		int noItems = 0;
		for (int i = 0; i < siteShopList.size(); i++) {
			if (itemsNeeded.contains(siteShopList.get(i))) {
				adder.get(i).click();
				noItems++;
				if (noItems == itemsNeeded.size())
					break;
			}
		}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		mp.goToCart();
		List<String> cartList = mp.getCartList();
		for (int i = 0; i < itemsNeeded.size(); i++) {
			if (!cartList.contains(itemsNeeded.get(i))) {
				Assert.fail("Item " + itemsNeeded.get(i) + " not present");
			}
		}
		log.info("Items added to cart");
		mp.goToCheckout();
		cp = new CheckoutPage(driver);
		cp.getPromoCode().sendKeys("rahulshettyacademy");
		cp.applyPromo().click();
		String checkPromoText = cp.getpromoText().getText();
		if(!checkPromoText.equals("Code applied ..!"))
		{
			if(checkPromoText.equals("Invalid code ..!"))
			{
				Assert.fail("Code expired");
			}
			else
			{
				Assert.fail("Promo code error");
			}
		}
		cp.finishOrder().click();
		log.info("Order is placed");

	}
	@AfterTest

	public void closeBrowser() {
		driver.close();
	}

}
