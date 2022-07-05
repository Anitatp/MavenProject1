package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	
	public WebDriver driver;
	
	
	private By promoCode = By.className("promoCode");
	private By applyButton = By.className("promoBtn");
	private By promoText = By.cssSelector("span.promoInfo");
	private By placeOrderButton =By.xpath("//button[contains(text(), 'Place Order')]");
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getPromoCode()
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.visibilityOfElementLocated(promoCode));
		return driver.findElement(promoCode);
	}
	
	public WebElement applyPromo()
	{
		return driver.findElement(applyButton);
	}
	
	public WebElement getpromoText()
	{	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.visibilityOfElementLocated(promoText));
		return driver.findElement(promoText);
	}
	
	public WebElement finishOrder()
	{
		return driver.findElement(placeOrderButton);
	}

}
