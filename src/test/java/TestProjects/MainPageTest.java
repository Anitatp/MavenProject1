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

import pageObjects.MainPage;
import resources.Initialization;


public class MainPageTest extends Initialization {
	public static Logger log = LogManager.getLogger(Initialization.class.getName());
	public WebDriver driver;
	public MainPage mp;

	@BeforeTest

	public void navigateToPage() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("Reached the HomePage");

	}

	@Test
	public void checkItems() {
		String[] shoppingList = { "Brocolli", "Cauliflower", "Cucumber", "Beetroot", "Carrot", "Tomato", "Beans",
				"Brinjal", "Capsicum", "Mushroom", "Potato", "Pumpkin", "Corn", "Onion", "Apple", "Banana", "Grapes",
				"Mango", "Musk Melon", "Orange", "Pears", "Pomegranate", "Raspberry", "Strawberry", "Water Melon",
				"Almonds", "Pista", "Nuts Mixture", "Cashews", "Walnuts" };
		mp = new MainPage(driver);
		List<String> siteShopList = mp.getShopList();
		for (int i = 0; i < shoppingList.length; i++) {
			if (!siteShopList.contains(shoppingList[i])) {
				Assert.fail("Item " + shoppingList[i] + " not present");
			}
		}
		log.info("Successfully validated the Shopping list");
	}

	@Test
	public void multiAddProducts() {
		mp = new MainPage(driver);
		mp.addOne();
		Assert.assertEquals("2", mp.getQuantity());
		mp.removeOne();
		Assert.assertEquals("1", mp.getQuantity());
		log.info("Checked the +,- buttons");
	

	}

	@Test

	public void testSearchBar() {
		mp = new MainPage(driver);
		WebElement searchbar = mp.searchProduct();
		searchbar.sendKeys("Tomato");
		mp.completeSearch();
		List<String> searchList = mp.getSearchResults();
		List<String> searched = Arrays.asList("Tomato");
		Assert.assertEquals(searched, searchList);;
		searchbar.clear();
		searchbar.sendKeys("2");
		String shownText = mp.getNoResultsText();
		Assert.assertEquals("Sorry, no products matched your search!", shownText);
		log.info("Tested the search bar");
		
	}

	@Test

	public void checkCart() {
		mp = new MainPage(driver);
		String itemNeeded = "Beetroot";
		List<String> siteShopList = mp.getShopList();
		List<WebElement> adder = mp.addItem();
		for (int i = 0; i < siteShopList.size(); i++) {
			if (itemNeeded.contains(siteShopList.get(i))) {
				adder.get(i).click();
				break;
			}
		}
		mp.goToCart();
		mp.removeItem();
		String message = "Your cart is empty!";
		String cartText = mp.getEmptyCartText();
		mp.goToCart();
		Assert.assertEquals(message,cartText);
		log.info("Tested the empty cart message");
	}


	@AfterTest

	public void closeBrowser() {
		driver.close();
	}
}
