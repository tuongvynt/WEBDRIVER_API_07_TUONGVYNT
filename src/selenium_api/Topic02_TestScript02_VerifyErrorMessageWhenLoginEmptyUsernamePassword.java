package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_TestScript02_VerifyErrorMessageWhenLoginEmptyUsernamePassword {
	WebDriver driver;

	@Test
	public void TestScript01_VerifyURLandTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Verify
		String stringErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(stringErrorMessage,"This is a required field.");
		
		//Verify 
		String stringErrorMessagePass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(stringErrorMessagePass,"This is a required field.");
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
