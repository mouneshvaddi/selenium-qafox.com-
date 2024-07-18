package registerpackage;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class demo {
	WebDriver  driver= null;
	
	@BeforeMethod
	public void bm() {
		driver =new ChromeDriver();
	}
	public void alert() throws InterruptedException {
		driver.get("https://letcode.in/alert");
		WebElement switchAlert = driver.findElement(By.id("accept")) ;
	switchAlert.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions. alertIsPresent());
		
		Alert alert =driver.switchTo().alert();
		Thread.sleep(1000);
		alert.accept();
	//	Thread.sleep(1000);
		
	 driver.switchTo().defaultContent();	

	// alert.accept();

	}
	
	@AfterMethod
	public void aam() {
		driver.quit();
		
	}

}
