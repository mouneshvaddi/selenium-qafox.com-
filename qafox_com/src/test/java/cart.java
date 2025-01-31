import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class cart {
	
	WebDriver  driver= null;
	 WebDriverWait wait = null;
	@BeforeMethod
	public void bm() {
		 driver = new ChromeDriver();
		 driver.get("https://bookcart.azurewebsites.net");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@Test
	public void caddingItemsToCartTestCaseOne() throws InterruptedException {
		System.out.println(3);
List<WebElement> categories = driver.findElements(By.xpath("//mat-list-item[@routerlink='/filter']//a"));
		
		int size = categories.size();
		
		for(int i=1;i<=size;i++) {
			
			String xpathText = "(//mat-list-item[@routerlink='/filter']//a)["+i+"]";
			WebElement category = driver.findElement(By.xpath(xpathText));
			category.click();
			WebElement firstBook = driver.findElement(By.xpath("(//mat-card[contains(@class,'book-card')]/a)[1]"));
			firstBook.click();
			WebElement addToCartButton = driver.findElement(By.xpath("(//span[contains(text(),'Add to Cart')])[1]"));
			addToCartButton.click();
			driver.navigate().back();
		}
		
		driver.navigate().refresh();
		
		WebElement cartOption = driver.findElement(By.xpath("//button[@*='/shopping-cart']"));
		cartOption.click();
		
		List<WebElement> prices = driver.findElements(By.xpath("//td[contains(@class,'cdk-column-total')]"));
		
		double totalPrice = 0;
		
		for(WebElement price : prices) {
			
			String priceText = price.getText().trim().replace("₹","").replace(",","");  //"123.00"
			double priceValue = Double.parseDouble(priceText);
			totalPrice = totalPrice + priceValue;
		
		}
		
		WebElement cartTotalPrice = driver.findElement(By.xpath("(//strong[text()='Cart Total:']/following::strong)[1]"));
		String cartTotalPriceText = cartTotalPrice.getText().trim().replace("₹","").replace(",","");;
		double caretTotalPriceValue = Double.parseDouble(cartTotalPriceText);
		
		Assert.assertEquals(totalPrice, caretTotalPriceValue);
		
		Thread.sleep(3000);
		
	}
	
	@AfterMethod
	public void am() {
		driver.quit();
		
	}
}
