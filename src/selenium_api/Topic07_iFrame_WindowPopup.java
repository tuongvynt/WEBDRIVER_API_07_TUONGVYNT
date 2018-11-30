package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_iFrame_WindowPopup {

	// Variable declaration
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 20);
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TestScript_01() {
		
		//Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		navigateToPage("http://www.hdfcbank.com/");
		
		
		// Step 02 - Close popup nếu có hiển thị
		List<WebElement> notification = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Number of element: " + notification.size() );
		
		if (notification.size() > 0)
		{
			// Switch to iframe notification
			driver.switchTo().frame(notification.get(0));
			Commons.clickElementByJavascript(driver, driver.findElement(By.xpath("//div[@id='div-close']")));
			System.out.println("Close popup successfully!");
			
			// Switch to default content
			driver.switchTo().defaultContent();
		}
		
		// Switch to lookingforiframe
 		WebElement lookingforiframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingforiframe);
		String messageText = driver.findElement(By.id("messageText")).getText();
 		Assert.assertTrue(messageText.equals("What are you looking for?"));
		driver.switchTo().defaultContent();
		
		// Switch to iframe (sliding image)
		WebElement iframeBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(iframeBanner);
		
		// Get size and verify
		List<WebElement> imagesBanner = driver.findElements(By.xpath("//img[@class='bannerimage']"));
 		Assert.assertTrue(imagesBanner.size() == 6);
		driver.switchTo().defaultContent();
		
		// Get size flipper banner
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		List<WebElement> banners = driver.findElements(By.xpath("//div[@class='flipBanner']//div[contains(@class,'product')]"));
		Assert.assertTrue(banners.size() == 8);
		
		// Check each flipper banners is displayed
		for (WebElement banner : banners)
		{
			System.out.println("Flipper banner = " + banner.isDisplayed() );
			Assert.assertTrue(banner.isDisplayed());
		}
	}
 	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void navigateToPage(String a) {
		driver.get(a);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
