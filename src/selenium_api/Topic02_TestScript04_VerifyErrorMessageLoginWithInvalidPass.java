package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_TestScript04_VerifyErrorMessageLoginWithInvalidPass {
	WebDriver driver;

	@Test
	public void TestScript01_VerifyURLandTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		
		// Enter invalid Email
		WebElement element = driver.findElement(By.id("pass"));
		element.click();
		element.clear();
		element.sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Verify Error message
		String stringErrorMessagePass = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(stringErrorMessagePass,"Please enter 6 or more characters without leading or trailing spaces.");
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
