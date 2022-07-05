package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	
	
	public WebDriver driver;
	
	private By shopList = By.cssSelector("h4.product-name");
	private By increment= By.cssSelector("a.increment");
	private By decrement = By.cssSelector("a.decrement");
	private By adder = By.xpath("//div[@class='product-action']/button");
	private By cartButton = By.cssSelector("img[alt='Cart']");
	private By cartList = By.cssSelector("p.product-name");
	private By remover = By.cssSelector("a.product-remove");
	private By emptyCartText = By.cssSelector("h2");
	private By quantity = By.cssSelector("input.quantity");
	private By searchBox = By.cssSelector("input[type='search']");
	private By searchButton = By.className("search-button");
	private By noResultsText = By.xpath("//div[@class='no-results']/h2");
	private By searchResultsText = By.xpath("//div[@class='product']/h4");
	private By proceedButton = By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]");
	public MainPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<String> getShopList() {
		
		List<WebElement> products =driver.findElements(shopList);
		List<String> formattedList = formatList(products);
		
		return formattedList;
		
	}
	
	public List<WebElement> addItem()
	{
		return driver.findElements(adder);
	}
	
	public List<WebElement> incrementation()
	{
		return driver.findElements(increment);
	}
	
	public List<WebElement> decrementation()
	{
		return driver.findElements(decrement);
	}
	
	public List<String> getCartList()
	{
		
		List<WebElement> products =driver.findElements(cartList);
		List<String> formattedList = formatList(products);
		
		return formattedList;
		
	}
	
	public void removeItem()
	{
		
			driver.findElement(remover).click();
			

	}
	
	public String getEmptyCartText()
	{
		return driver.findElement(emptyCartText).getText();
	}
	
	public void addOne()
	{
		driver.findElement(increment).click();
	}
	
	public void removeOne()
	{
		driver.findElement(decrement).click();
	}
	
	public String getQuantity()
	{
		return driver.findElement(quantity).getAttribute("value");
	}
	
	public WebElement searchProduct()
	{
		return driver.findElement(searchBox);
	}
	
	public void completeSearch()
	{
		driver.findElement(searchButton).click();
	}
	
	public String getNoResultsText()
	{
		return driver.findElement(noResultsText).getText();
	}
	
	public List<String> getSearchResults()
	{
		List<WebElement> products =driver.findElements(searchResultsText);
		List<String> formattedList = formatList(products);
		return formattedList;
	}
	
	public void goToCart()
	{
		driver.findElement(cartButton).click();
	}
	
	public void goToCheckout()
	{
		driver.findElement(proceedButton).click();
	}
	
	public List<String> formatList(List<WebElement> unformattedList)
	{
		List<String> formattedList = new ArrayList<String>();
		for (int i = 0; i < unformattedList.size(); i++) {
			String[] name = unformattedList.get(i).getText().split("-");
			formattedList.add(i, name[0].trim());
		}
		
		return formattedList;
	}
}
