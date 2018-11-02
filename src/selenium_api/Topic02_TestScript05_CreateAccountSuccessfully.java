package selenium_api;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_TestScript05_CreateAccountSuccessfully {
	WebDriver driver;

	@Test
	public void TestScript01_VerifyURLandTitle() {
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();;
		
		// Generate Email address
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String stringEmail = "nttvy"+ stringDate + "@mailinator.com";
		
		// Enter value
		WebElement elementFirstname = driver.findElement(By.id("firstname"));
		elementFirstname.click();
		elementFirstname.clear();
		elementFirstname.sendKeys("Vy");
		WebElement elementMiddlename = driver.findElement(By.id("middlename"));
		elementMiddlename.click();
		elementMiddlename.clear();
		elementMiddlename.sendKeys("Thi Tuong");
		WebElement elementLastname = driver.findElement(By.id("lastname"));
		elementLastname.click();
		elementLastname.clear();
		elementLastname.sendKeys("Nguyen");
		WebElement elementEmail = driver.findElement(By.id("email_address"));
		elementEmail.click();
		elementEmail.clear();
		elementEmail.sendKeys(stringEmail);
		WebElement elementPassword = driver.findElement(By.id("password"));
		elementPassword.click();
		elementPassword.clear();
		elementPassword.sendKeys("Admin@1234");
		WebElement elementConfirmation = driver.findElement(By.id("confirmation"));
		elementConfirmation.click();
		elementConfirmation.clear();
		elementConfirmation.sendKeys("Admin@1234");
		
		// Click on Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		
		// Verify message
		String stringMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(stringMessage,"Thank you for registering with Main Website Store.");
		
		// Logout
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("Log Out")).click();
	
		// Need to update
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Verify Home page
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
