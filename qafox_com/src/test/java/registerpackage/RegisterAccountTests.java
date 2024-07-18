package registerpackage;

import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.common.base.Verify;
import com.google.common.io.Files;

import freemarker.core.ReturnInstruction.Return;

public class RegisterAccountTests {
	
WebDriver  driver= null;
WebElement myAccountDropMenu = null;
	
	@BeforeMethod
		public  void beforetestt() throws InterruptedException {
		 Map<String, Object> prefs = new HashMap<>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		 options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	//	System.setProperty("webdriver.chormedriver","C:\\Users\\Monish\\eclipse-workspace\\project1\\flipkartwithselenium\\project_1\\driver\\googledriver");
		driver = new  ChromeDriver(options);
		 driver.get("https://tutorialsninja.com/demo/");
		 driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
		    WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
		    account.click();
		    
		    Thread.sleep(1000);
		    
		    WebElement register= driver.findElement(By.linkText("Register"));
		    register.click();
		    Thread.sleep(2000);
		
		}
	

	
	@Test	(priority=1)
	public void registerAccountWithMandatoryFields()  throws InterruptedException{
	    driver.get("https://tutorialsninja.com/demo/");
	 //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
	    WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
	    account.click();
	    Thread.sleep(2000);
	 
//	    WebElement myAccount= driver.findElement(By.xpath("//span[text()='My Account']"));
//	    myAccount.click();
	    
	    WebElement register= driver.findElement(By.linkText("Register"));
	    register.click();
	    Thread.sleep(2000);
	    WebElement firstname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
	 //   WebElement firstname= driver.findElement(By.id("input-firstname"));
	    firstname.sendKeys("mounesh",Keys.ENTER);
	    Thread.sleep(2000);
	  
	    WebElement lastname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastname")));
	  //  WebElement lastname= driver.findElement(By.id("input-lastnamer"));
	    lastname.sendKeys("vaddi",Keys.ENTER);

	    
	    Date date = new Date();
	   
	    WebElement email =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-email")));
	 //   WebElement email= driver.findElement(By.linkText("input-email"));
	    email.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
	    Thread.sleep(1000);
	    
	   // WebElement telephone =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastnamer")));
	    WebElement telephone= driver.findElement(By.id("input-telephone"));
    telephone.sendKeys("9381017480");
    Thread.sleep(2000);
    
    WebElement password =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-password")));
	//    WebElement password= driver.findElement(By.linkText("input-password"));
	    password.sendKeys("123456");
	    Thread.sleep(2000);
	    
	    WebElement confirmpassword =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-confirm")));
	 //   WebElement confirmpassword= driver.findElement(By.linkText("input-confirm"));
	    confirmpassword.sendKeys("123456");
	    Thread.sleep(2000);
	    
	    WebElement privacy =  wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
	//    WebElement privacy = driver.findElement(By.name("c"));
	    privacy.click();
	    
	     WebElement submit = driver.findElement(By.xpath("//input[@value='Continue']"));
	    submit.click();
	    Thread.sleep(1000);
	    
	    
	     account = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
	    account.click();
	    Thread.sleep(1000);
	    
	    
         WebElement logoutOption = driver.findElement(By.linkText("Logout"));
		Assert.assertTrue(logoutOption.isDisplayed());
		System.out.println("it is displayed");
	    Thread.sleep(1000);
	    
	    
	    String expectedHeading = "Your Account Has Been Created!";
	    Thread.sleep(1000);
		WebElement headingElement = driver.findElement(By.xpath("//div[@id='content']/h1"));
		 Thread.sleep(1000);
		String actualHeading = headingElement.getText();
		 Thread.sleep(1000);
		Assert.assertEquals(actualHeading, expectedHeading);
		System.out.println("it is has same heading name");
	   
	    
	
		WebElement actualCongradulationsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p"));
		String actualCongradulationsText = actualCongradulationsElement.getText();
		String expectedCongradulationsText = "Congratulations! Your new account has been successfully created!";
		Assert.assertEquals(actualCongradulationsText,expectedCongradulationsText);
		
		
		WebElement actualmemberPrivilegesElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[2]"));
		String actualmemberPrivilegesText = actualmemberPrivilegesElement.getText();
		String expectedmemberPrivilegesText = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		Assert.assertTrue(actualmemberPrivilegesText.contains(expectedmemberPrivilegesText));
		
		WebElement actualmemberQuestionsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[3]"));
		String actualQuestionsText = actualmemberQuestionsElement.getText();
		String expectedQuestionsText = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		Assert.assertTrue(actualQuestionsText.contains(expectedQuestionsText));
		
		WebElement actualConfirmationEmailElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[4]"));
		String actualConfirmationEmailText = actualConfirmationEmailElement.getText();
		String expectedConfirmationEmailText = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
		Assert.assertTrue(actualConfirmationEmailText.contains(expectedConfirmationEmailText));
		
		WebElement contactUsLinkElement = driver.findElement(By.linkText("contact us"));
		Assert.assertTrue(contactUsLinkElement.isDisplayed());
		
		WebElement continueButtonElement = driver.findElement(By.xpath("//a[text()='Continue']"));
		continueButtonElement.click();
		
		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = "My Account";
		AssertJUnit.assertEquals(actualPageTitle,expectedPageTitle);
	    
	    
	}
	
	
	
	@Test(priority=2)
	public void registerAccountWithAllFields()  throws InterruptedException {

		 driver.get("https://tutorialsninja.com/demo/");
		 //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
		    WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
		    account.click();
		    Thread.sleep(2000);
		 
//		    WebElement myAccount= driver.findElement(By.xpath("//span[text()='My Account']"));
//		    myAccount.click();
		    
		    WebElement register= driver.findElement(By.linkText("Register"));
		    register.click();
		    Thread.sleep(2000);
		    WebElement firstname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
		 //   WebElement firstname= driver.findElement(By.id("input-firstname"));
		    firstname.sendKeys("mounesh",Keys.ENTER);
		    Thread.sleep(2000);
		  
		    WebElement lastname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastname")));
		  //  WebElement lastname= driver.findElement(By.id("input-lastnamer"));
		    lastname.sendKeys("vaddi",Keys.ENTER);

		    
		    Date date = new Date();
		   
		    WebElement email =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-email")));
		 //   WebElement email= driver.findElement(By.linkText("input-email"));
		    email.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
		    Thread.sleep(1000);
		    
		   // WebElement telephone =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastnamer")));
		    WebElement telephone= driver.findElement(By.id("input-telephone"));
	    telephone.sendKeys("9381017480");
	    Thread.sleep(2000);
	    
	    WebElement password =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-password")));
		//    WebElement password= driver.findElement(By.linkText("input-password"));
		    password.sendKeys("123456");
		    Thread.sleep(2000);
		    
		    WebElement confirmpassword =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-confirm")));
		 //   WebElement confirmpassword= driver.findElement(By.linkText("input-confirm"));
		    confirmpassword.sendKeys("123456");
		    Thread.sleep(2000);
		    
		    WebElement privacy =  wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
		//    WebElement privacy = driver.findElement(By.name("c"));
		    privacy.click();
		    
		     WebElement submit = driver.findElement(By.xpath("//input[@value='Continue']"));
		    submit.click();
		    Thread.sleep(1000);;
		
		myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccountDropMenu.click();
		
		WebElement logoutOption = driver.findElement(By.linkText("Logout"));
		
		Assert.assertTrue(logoutOption.isDisplayed());
		String actualURL = driver.getCurrentUrl();
		String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/success";
		Assert.assertEquals(actualURL,expectedURL);
		String expectedHeading = "Your Account Has Been Created!";
		WebElement headingElement = driver.findElement(By.xpath("//div[@id='content']/h1"));
		String actualHeading = headingElement.getText();
		Assert.assertEquals(actualHeading, expectedHeading);
		WebElement actualCongradulationsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p"));
		String actualCongradulationsText = actualCongradulationsElement.getText();
		String expectedCongradulationsText = "Congratulations! Your new account has been successfully created!";
		Assert.assertEquals(actualCongradulationsText,expectedCongradulationsText);
		WebElement actualmemberPrivilegesElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[2]"));
		String actualmemberPrivilegesText = actualmemberPrivilegesElement.getText();
		String expectedmemberPrivilegesText = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		Assert.assertTrue(actualmemberPrivilegesText.contains(expectedmemberPrivilegesText));
		WebElement actualmemberQuestionsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[3]"));
		String actualQuestionsText = actualmemberQuestionsElement.getText();
		String expectedQuestionsText = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		Assert.assertTrue(actualQuestionsText.contains(expectedQuestionsText));
		WebElement actualConfirmationEmailElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[4]"));
		String actualConfirmationEmailText = actualConfirmationEmailElement.getText();
		String expectedConfirmationEmailText = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
		Assert.assertTrue(actualConfirmationEmailText.contains(expectedConfirmationEmailText));
		WebElement contactUsLinkElement = driver.findElement(By.linkText("contact us"));
		Assert.assertTrue(contactUsLinkElement.isDisplayed());
		
		WebElement continueButtonElement = driver.findElement(By.xpath("//a[text()='Continue']"));
		continueButtonElement.click();
		
		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = "My Account";
		Assert.assertEquals(actualPageTitle,expectedPageTitle);
		
		
	}
@Test(priority=3)
	public void registerWithoutProvidingAnyFields() throws InterruptedException {
		 driver.get("https://tutorialsninja.com/demo/");
		 //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
		    WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
		    account.click();
		    Thread.sleep(2000);
		 
//		    WebElement myAccount= driver.findElement(By.xpath("//span[text()='My Account']"));
//		    myAccount.click();
		    
		    WebElement register= driver.findElement(By.linkText("Register"));
		    register.click();
		    Thread.sleep(2000);
		    WebElement firstname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
		    firstname.click();
		    WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
			continueButton.click();
			
			String actualFirstNameWarningMessage = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
			String expectedFirstNameWarningMessage = "First Name must be between 1 and 32 characters!";
			Assert.assertEquals(actualFirstNameWarningMessage, expectedFirstNameWarningMessage);
		
			String actualLastNameWarningMessage = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
			String expectedLastNameWarningMessage = "Last Name must be between 1 and 32 characters!";
			Assert.assertEquals(actualLastNameWarningMessage, expectedLastNameWarningMessage);
			
			String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
			String expectedEmailWarningMessage = "E-Mail Address does not appear to be valid!";
			Assert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
			
			String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
			String expectedTelephoneWarningMessage = "Telephone must be between 3 and 32 characters!";
			Assert.assertEquals(actualTelephoneWarningMessage, expectedTelephoneWarningMessage);
			
			String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
			String expectedPasswordWarningMessage = "Password must be between 4 and 20 characters!";
			Assert.assertEquals(actualPasswordWarningMessage, expectedPasswordWarningMessage);
		
			String actualPrivacyPolicyMessage = driver.findElement(By.xpath("//div[@id='account-register']/div[1]")).getText();
			String expectedPrivacyPolicyMessage = "Warning: You must agree to the Privacy Policy!";
			Assert.assertTrue(actualPrivacyPolicyMessage.contains(expectedPrivacyPolicyMessage));

            
		    
		
	}
@Test(priority=4)
public void registerAccountBySelectingYesNewsletterOption() throws InterruptedException  {
	
	    
	
	WebElement firstNameField = driver.findElement(By.id("input-firstname"));
	firstNameField.sendKeys("Arun");
	
	WebElement lastNameField = driver.findElement(By.id("input-lastname"));
	lastNameField.sendKeys("Motoori");
	
	Date date = new Date();
	WebElement emailField = driver.findElement(By.id("input-email"));
	emailField.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
	
	WebElement telephoneField = driver.findElement(By.id("input-telephone"));
	telephoneField.sendKeys("1234567890");
	
	WebElement passwordField = driver.findElement(By.id("input-password"));
	passwordField.sendKeys("12345");
	
	WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
	passwordConfirmField.sendKeys("12345");
	
	WebElement yesFieldElement = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
	yesFieldElement.click();
	
	WebElement privacyPolicyField = driver.findElement(By.name("agree"));
	privacyPolicyField.click();
	
	WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
	continueButton.click();
	
	myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
	myAccountDropMenu.click();
	WebElement logoutOption = driver.findElement(By.linkText("Logout"));
	Assert.assertTrue(logoutOption.isDisplayed());
	
	String actualURL = driver.getCurrentUrl();
	String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/success";
	Assert.assertEquals(actualURL,expectedURL);
	
	String expectedHeading = "Your Account Has Been Created!";
	WebElement headingElement = driver.findElement(By.xpath("//div[@id='content']/h1"));
	String actualHeading = headingElement.getText();
	Assert.assertEquals(actualHeading, expectedHeading);
	WebElement actualCongradulationsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p"));
	String actualCongradulationsText = actualCongradulationsElement.getText();
	String expectedCongradulationsText = "Congratulations! Your new account has been successfully created!";
	Assert.assertEquals(actualCongradulationsText,expectedCongradulationsText);
	WebElement actualmemberPrivilegesElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[2]"));
	String actualmemberPrivilegesText = actualmemberPrivilegesElement.getText();
	String expectedmemberPrivilegesText = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
	Assert.assertTrue(actualmemberPrivilegesText.contains(expectedmemberPrivilegesText));
	WebElement actualmemberQuestionsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[3]"));
	String actualQuestionsText = actualmemberQuestionsElement.getText();
	String expectedQuestionsText = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
	Assert.assertTrue(actualQuestionsText.contains(expectedQuestionsText));
	WebElement actualConfirmationEmailElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[4]"));
	String actualConfirmationEmailText = actualConfirmationEmailElement.getText();
	String expectedConfirmationEmailText = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
	Assert.assertTrue(actualConfirmationEmailText.contains(expectedConfirmationEmailText));
	WebElement contactUsLinkElement = driver.findElement(By.linkText("contact us"));
	Assert.assertTrue(contactUsLinkElement.isDisplayed());
	
	WebElement continueButtonElement = driver.findElement(By.xpath("//a[text()='Continue']"));
	continueButtonElement.click();
	
	String actualPageTitle = driver.getTitle();
	String expectedPageTitle = "My Account";
	Assert.assertEquals(actualPageTitle,expectedPageTitle);
	
	WebElement subscribeUnsubscribeNewsletterOption = driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter"));
	subscribeUnsubscribeNewsletterOption.click();
	boolean yesRadioButtonState = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected();
	Assert.assertTrue(yesRadioButtonState);

}

@Test(priority=5)
public void registerAccountBySelectingNoNewsletterOption()  {

	
	WebElement firstNameField = driver.findElement(By.id("input-firstname"));
	firstNameField.sendKeys("Arun");
	
	WebElement lastNameField = driver.findElement(By.id("input-lastname"));
	lastNameField.sendKeys("Motoori");
	
	Date date = new Date();
	WebElement emailField = driver.findElement(By.id("input-email"));
	emailField.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
	
	WebElement telephoneField = driver.findElement(By.id("input-telephone"));
	telephoneField.sendKeys("1234567890");
	
	WebElement passwordField = driver.findElement(By.id("input-password"));
	passwordField.sendKeys("12345");
	
	WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
	passwordConfirmField.sendKeys("12345");
	
	WebElement noRadioFieldElement = driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']"));
	noRadioFieldElement.click();
	
	WebElement privacyPolicyField = driver.findElement(By.name("agree"));
	privacyPolicyField.click();
	
	WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
	continueButton.click();
	
	myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
	myAccountDropMenu.click();
	WebElement logoutOption = driver.findElement(By.linkText("Logout"));
	Assert.assertTrue(logoutOption.isDisplayed());
	
	String actualURL = driver.getCurrentUrl();
	String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/success";
	Assert.assertEquals(actualURL,expectedURL);
	
	String expectedHeading = "Your Account Has Been Created!";
	WebElement headingElement = driver.findElement(By.xpath("//div[@id='content']/h1"));
	String actualHeading = headingElement.getText();
	Assert.assertEquals(actualHeading, expectedHeading);
	WebElement actualCongradulationsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p"));
	String actualCongradulationsText = actualCongradulationsElement.getText();
	String expectedCongradulationsText = "Congratulations! Your new account has been successfully created!";
	Assert.assertEquals(actualCongradulationsText,expectedCongradulationsText);
	WebElement actualmemberPrivilegesElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[2]"));
	String actualmemberPrivilegesText = actualmemberPrivilegesElement.getText();
	String expectedmemberPrivilegesText = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
	Assert.assertTrue(actualmemberPrivilegesText.contains(expectedmemberPrivilegesText));
	WebElement actualmemberQuestionsElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[3]"));
	String actualQuestionsText = actualmemberQuestionsElement.getText();
	String expectedQuestionsText = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
	Assert.assertTrue(actualQuestionsText.contains(expectedQuestionsText));
	WebElement actualConfirmationEmailElement = driver.findElement(By.xpath("//div[@id='content']/h1/following-sibling::p[4]"));
	String actualConfirmationEmailText = actualConfirmationEmailElement.getText();
	String expectedConfirmationEmailText = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
	Assert.assertTrue(actualConfirmationEmailText.contains(expectedConfirmationEmailText));
	WebElement contactUsLinkElement = driver.findElement(By.linkText("contact us"));
	Assert.assertTrue(contactUsLinkElement.isDisplayed());
	
	WebElement continueButtonElement = driver.findElement(By.xpath("//a[text()='Continue']"));
	continueButtonElement.click();
	
	String actualPageTitle = driver.getTitle();
	String expectedPageTitle = "My Account";
	Assert.assertEquals(actualPageTitle,expectedPageTitle);
	
	WebElement subscribeUnsubscribeNewsletterOption = driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter"));
	subscribeUnsubscribeNewsletterOption.click();
	boolean noRadioButtonState = driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected();
	Assert.assertTrue(noRadioButtonState);
	
}

@Test(priority=6)
public void registerAccountPageNavigationsDifferentWays() {
	
	String expectedTitle = "Register Account";
	String actualTitle = driver.getTitle();
	
	Assert.assertEquals(actualTitle,expectedTitle);
	
	myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
	myAccountDropMenu.click();
	
	WebElement loginOption = driver.findElement(By.linkText("Login"));
	loginOption.click();
	
	WebElement continueButton = driver.findElement(By.xpath("//a[text()='Continue']"));
	continueButton.click();

	actualTitle = driver.getTitle();
	
	Assert.assertEquals(actualTitle,expectedTitle);
	
	myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
	myAccountDropMenu.click();
	
	loginOption = driver.findElement(By.linkText("Login"));
	loginOption.click();
	
	WebElement registerColumnOption = driver.findElement(By.xpath("//div[@class='list-group']/a[text()='Register']"));
	registerColumnOption.click();
	
	actualTitle = driver.getTitle();
	Assert.assertEquals(actualTitle,expectedTitle);

 }

@Test(priority=7)
public void registerAccountByProvidingMismatchingPasswords()  {
	
	driver.findElement(By.id("input-firstname")).sendKeys("Arun");
	driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
	Date date = new Date();
	WebElement emailField = driver.findElement(By.id("input-email"));
	emailField.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	driver.findElement(By.id("input-password")).sendKeys("12345");
	driver.findElement(By.id("input-confirm")).sendKeys("123456");
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	
	String actualWarningMessage = driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText();
	String expectedWarningMessage = "Password confirmation does not match password!";
	Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
	
	
}

@Test(priority=8)
public void registerDuplicateAccountWithSameEmail()  {
	
	driver.findElement(By.id("input-firstname")).sendKeys("Arun");
	driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
	driver.findElement(By.id("input-email")).sendKeys("amotooricap1@gmail.com");
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	driver.findElement(By.id("input-password")).sendKeys("12345");
	driver.findElement(By.id("input-confirm")).sendKeys("123456");
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	
	String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
	String actualWarningMessage = driver.findElement(By.xpath("//div[@id='account-register']/div[1]")).getText();
	Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	
}


@Test(dataProvider = "invalidEmailSupplier",priority=9)
public void registerAccountUsingInvalidEmailAddress(String invalidEmail) throws InterruptedException, IOException {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
	    WebElement firstname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
	 //   WebElement firstname= driver.findElement(By.id("input-firstname"));
	    firstname.sendKeys("mounesh",Keys.ENTER);
	    Thread.sleep(2000);
	  
	    WebElement lastname =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastname")));
	  //  WebElement lastname= driver.findElement(By.id("input-lastnamer"));
	    lastname.sendKeys("vaddi",Keys.ENTER);

	    
	  
	   
	    WebElement email =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-email")));
	 //   WebElement email= driver.findElement(By.linkText("input-email"));
	 //   email.sendKeys(date.toString().replace(" ","_").replace(":","_")+"@gmail.com");
	    email.sendKeys(invalidEmail);
	    Thread.sleep(1000);
	    
	   // WebElement telephone =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-lastnamer")));
	    WebElement telephone= driver.findElement(By.id("input-telephone"));
 telephone.sendKeys("9381017480");
 Thread.sleep(2000);
 
 WebElement password =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-password")));
	//    WebElement password= driver.findElement(By.linkText("input-password"));
	    password.sendKeys("123456");
	    Thread.sleep(2000);
	    
	    WebElement confirmpassword =  wait.until(ExpectedConditions.elementToBeClickable(By.id("input-confirm")));
	 //   WebElement confirmpassword= driver.findElement(By.linkText("input-confirm"));
	    confirmpassword.sendKeys("123456");
	    Thread.sleep(2000);
	    
	    WebElement privacy =  wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
	//    WebElement privacy = driver.findElement(By.name("c"));
	    privacy.click();
	    
	     WebElement submit = driver.findElement(By.xpath("//input[@value='Continue']"));
	    submit.click();
	    Thread.sleep(1000);
	    
	    Date date = new Date();
	    String timestamp=date.toString().replace("", "_").replace(":","_");
	    
	    
	    File scrn = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    Files.copy(scrn,new File("screenshots//screenshot_"+timestamp+".png"));
	    
	   
	
	    String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualURL = driver.getCurrentUrl();
		
		Assert.assertEquals(actualURL, expectedURL);
}

@DataProvider(name="invalidEmailSupplier")
public String[] supplyInvalidEmailData() {
String[] invalidEmails = {"amotoori","amotoori@","amotoori@gmail","amotoori@gmail."};
	//String[] invalidEmails = {"amotoori"};
return invalidEmails;
}


@Test(priority=10,dataProvider="invalidTelephoneSupplier")
public void registerAccountUsingInvalidTelephoneNumber(String telephoneNumber) throws IOException {
	
	WebElement firstNameField = driver.findElement(By.id("input-firstname"));
	firstNameField.sendKeys("Arun");
	
	WebElement lastNameField = driver.findElement(By.id("input-lastname"));
	lastNameField.sendKeys("Motoori");
	
	Date date = new Date();
	String timeStamp = date.toString().replace(" ","_").replace(":","_");
	WebElement emailField = driver.findElement(By.id("input-email"));
	emailField.sendKeys(timeStamp+"@gmail.com");
	
	WebElement passwordField = driver.findElement(By.id("input-password"));
	passwordField.sendKeys("12345");
	
	WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
	passwordConfirmField.sendKeys("12345");
	
	WebElement yesNewsletterOption = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
	yesNewsletterOption.click();
	
	WebElement privacyPolicyField = driver.findElement(By.name("agree"));
	privacyPolicyField.click();
	
	WebElement telephoneField = driver.findElement(By.id("input-telephone"));
	telephoneField.sendKeys(telephoneNumber);
	
	WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
	continueButton.click();
	
	String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
	String actualURL = driver.getCurrentUrl();
	

	
	
	Assert.assertEquals(actualURL, expectedURL);
	
	String expectedWarningMessage = "Invlaid telephone number is entered.";
	String actualWarningMessage = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
	
	Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
    String timestamp=date.toString().replace("", "_").replace(":","_");
    
	 File scrn = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    Files.copy(scrn,new File("screenshots//phonenoscreenshot_"+timestamp+".png"));
	
}
@DataProvider(name="invalidTelephoneSupplier")
public String[] supplyInvalidTelephoneData() {
	
	String[] invalidTelephones = {"111","abcde"};
	return invalidTelephones;
	
}
@Test(priority=11)
public void registerAccountUsingKeyboardKeys() {

	Actions actions = new Actions(driver);
	
	for(int i=1;i<=23;i++) {
		actions.sendKeys(Keys.TAB).perform();
	}
	
	Date date = new Date();
	String timeStamp = date.toString().replace(" ","_").replace(":","_");
	
	actions.sendKeys("Arun")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys("Motoori")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys(timeStamp+"@gmail.com")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys("1234567890")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys("12345")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys("12345")
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.ARROW_LEFT)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.SPACE)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.TAB)
	.pause(Duration.ofSeconds(1))
	.sendKeys(Keys.ENTER).build().perform();
	
	String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/success";
	String actualURL = driver.getCurrentUrl();
	Assert.assertEquals(actualURL, expectedURL);
	
}


@Test(priority=12)
public void regiserAccountPlaceholdersChecking() {
	
	WebElement firstNameField = driver.findElement(By.id("input-firstname"));
	String actualFirstNamePlaceHolder = firstNameField.getAttribute("placeholder");
	String expectedFirstNamePlaceHolder = "First Name";
	Assert.assertEquals(actualFirstNamePlaceHolder, expectedFirstNamePlaceHolder);
	
	WebElement lastNameField = driver.findElement(By.id("input-lastname"));
	String actualLastNamePlaceHolder = lastNameField.getAttribute("placeholder");
	String expectedLastNamePlaceHolder = "Last Name";
	Assert.assertEquals(actualLastNamePlaceHolder, expectedLastNamePlaceHolder);
	
	WebElement emailField = driver.findElement(By.id("input-email"));
	String actualEmailPlaceHolder = emailField.getAttribute("placeholder");
	String expectedEmailPlaceHolder = "E-Mail";
	Assert.assertEquals(actualEmailPlaceHolder, expectedEmailPlaceHolder);
	
	WebElement telephoneField = driver.findElement(By.id("input-telephone"));
	String actualTelephonePlaceHolder = telephoneField.getAttribute("placeholder");
	String expectedTelephonePlaceHolder = "Telephone";
	Assert.assertEquals(actualTelephonePlaceHolder, expectedTelephonePlaceHolder);
	
	WebElement passwordField = driver.findElement(By.id("input-password"));
	String actualPasswordPlaceHolder = passwordField.getAttribute("placeholder");
	String expectedPasswordPlaceHolder = "Password";
	Assert.assertEquals(actualPasswordPlaceHolder, expectedPasswordPlaceHolder);
	
	WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
	String actualPasswordConfirmPlaceHolder = passwordConfirmField.getAttribute("placeholder");
	String expectedPasswordConfirmPlaceHolder = "Password Confirm";
	Assert.assertEquals(actualPasswordConfirmPlaceHolder, expectedPasswordConfirmPlaceHolder);		
	
}

@Test(priority=13)
public void registerAccountMandatoryFieldsAsterickSymbolCheck() {
	WebElement firstNameLabel = driver.findElement(By.xpath("//label[@for='input-firstname']"));
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	//jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');", firstNameLabel);
	String actualFirstNameAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",firstNameLabel);
	Assert.assertTrue(actualFirstNameAsterickContent.contains("*"));
	String actualFirstNameAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",firstNameLabel);
	Assert.assertEquals(actualFirstNameAsterickColor,"rgb(255, 0, 0)");
	
	WebElement lastNameLabel = driver.findElement(By.xpath("//label[@for='input-lastname']"));
	String actualLastNameAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",lastNameLabel);
	Assert.assertTrue(actualLastNameAsterickContent.contains("*"));
	String actualLastNameAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",lastNameLabel);
	Assert.assertEquals(actualLastNameAsterickColor,"rgb(255, 0, 0)");
	
	WebElement emailLabel = driver.findElement(By.xpath("//label[@for='input-email']"));
	String actualEmailAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",emailLabel);
	Assert.assertTrue(actualEmailAsterickContent.contains("*"));
	String actualEmailAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",emailLabel);
	Assert.assertEquals(actualEmailAsterickColor,"rgb(255, 0, 0)");
	
	WebElement telephoneLabel = driver.findElement(By.xpath("//label[@for='input-telephone']"));
	String actualTelephoneAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",telephoneLabel);
	Assert.assertTrue(actualTelephoneAsterickContent.contains("*"));
	String actualTelephoneAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",telephoneLabel);
	Assert.assertEquals(actualTelephoneAsterickColor,"rgb(255, 0, 0)");
	
	WebElement passwordLabel = driver.findElement(By.xpath("//label[@for='input-password']"));
	String actualPasswordAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",passwordLabel);
	Assert.assertTrue(actualPasswordAsterickContent.contains("*"));
	String actualPasswordAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",passwordLabel);
	Assert.assertEquals(actualPasswordAsterickColor,"rgb(255, 0, 0)");
	
	WebElement passwordConfirmLabel = driver.findElement(By.xpath("//label[@for='input-confirm']"));
	String actualPasswordConfirmAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",passwordConfirmLabel);
	Assert.assertTrue(actualPasswordConfirmAsterickContent.contains("*"));
	String actualPasswordConfirmAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",passwordConfirmLabel);
	Assert.assertEquals(actualPasswordConfirmAsterickColor,"rgb(255, 0, 0)");
	
	WebElement privacyPolicyLabel = driver.findElement(By.xpath("//div[@class='pull-right']"));
	String privacyPolicyAsterickContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",privacyPolicyLabel);
	Assert.assertTrue(privacyPolicyAsterickContent.contains("*"));
	String privacyPolicyAsterickColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color');",privacyPolicyLabel);
	Assert.assertEquals(privacyPolicyAsterickColor,"rgb(255, 0, 0)");
	

	
}





@AfterMethod
	public void finaltest1()  {
		System.out.println("completed sucessfully");
		driver.quit();
		
	}
	}






