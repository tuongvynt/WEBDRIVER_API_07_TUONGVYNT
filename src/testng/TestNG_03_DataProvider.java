package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_03_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void setupBrowser() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "UserPassword")
	public void TC_01_LoginToSystem(String email, String password) {
		
		// Click to navigate to Login form
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Login
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();

		// Verify User
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class= 'box-content']/p[contains(.,'" + email + "')]"))				.isDisplayed());

		// Logout
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

		// Verify navigate to HomePage
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	@DataProvider
	public Object[][] UserPassword() {
		return new Object[][] {
			{ "nttvy01@mailinator.com", "123123" },
			{ "nttvy02@mailinator.com", "123123" },
			{ "nttvy03@mailinator.com", "123123" } };
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
