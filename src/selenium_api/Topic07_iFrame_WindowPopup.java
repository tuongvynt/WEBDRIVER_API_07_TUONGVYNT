package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	//@Test
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
	
	@Test
	public void TestScript_02_WindowTitle() {
		  navigateToPage("http://www.hdfcbank.com/");
		  String parentID = driver.getWindowHandle();
		  
		  // Check if popup is displayed or not
		  List <WebElement> notification = driver.findElements(By.xpath("//iframe[@id ='vizury-notification-template']"));
		  System.out.println("Number of element =" + notification.size()); 
		  if(notification.size() >0) {
			  driver.switchTo().frame(notification.get(0));
			  clickElementByJavascript(driver.findElement(By.cssSelector("#div-close")));
			  System.out.println("Close popup success");
			  driver.switchTo().defaultContent();
		  }
		  
		  //Click on Agri link
		  driver.findElement(By.xpath("//a[text() ='Agri']")).click();
		  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		  Assert.assertEquals(driver.getTitle(), "HDFC Bank Kisan Dhan Vikas e-Kendra");
		  
		  //Click on Account Details link
		  driver.findElement(By.xpath("//a[contains(., 'Account Details')]")).click();
		  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		  Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");
		  
		  //Click on Privacy Policy link
		  //Switch to iframe contains Privacy Policy link
		  driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name ='footer']")));
		  driver.findElement(By.xpath("//a[text() = 'Privacy Policy']")).click();
		  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		  Assert.assertEquals(driver.getTitle(), "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		   
		  //Click on CSR link on Privacy Policy page
		  driver.findElement(By.xpath("//a[text() = 'CSR']")).click();
		  Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");
		  
		  //Close all tabs excepts parent window
		  closeAllWindowsWithoutParentWindows(parentID);
	  }
	  
	  //@Test
	  public void TestScript_03_AddToCompare() {
		  navigateToPage("http://live.guru99.com/index.php/");
		  String parentID = driver.getWindowHandle();
		  
		  driver.findElement(By.xpath("//a[text() ='Mobile']")).click();
		  
		  //Add products: Sony Xperia and Samsung to compare
		  driver.findElement(By.xpath("//a[text() ='Sony Xperia']/parent::h2/following-sibling::div[@class ='actions']//a[text() ='Add to Compare']")).click();
		  driver.findElement(By.xpath("//a[text() ='Samsung Galaxy']/parent::h2/following-sibling::div[@class ='actions']//a[text() ='Add to Compare']")).click();
		  
		  driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
		  switchToWindowByTitle("Products Comparison List - Magento Commerce");
		  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		  
		  closeAllWindowsWithoutParentWindows(parentID);
		  
	  }
	  
	  //Dung trong truong hop co duy nhat 2 cua so
	  public void switchToWindowByID(String parentID) {
		  //Get ra tat ca cac cua so dang co
	      Set<String> allWindows = driver.getWindowHandles();
	      // dung vong lap de kiem tra. Vong lap for x duyet qua tat ca cua so
	      for (String runWindow : allWindows) {
	    	  System.out.println(runWindow);
	    	  // Moi lan duyet kiem tra dieu kien neu ID khac vs parentID thi no switch qua
	                  if (!runWindow.equals(parentID)) {
	                              driver.switchTo().window(runWindow);
	                              //break khoi vong lap for khong kiem tra nua
	                              break;
	                  }
	      }
	  }
	  
	  // Dung trong truong hop co tu 2 cua so tro len
	  public void switchToWindowByTitle(String expectedTitle) {
		  // Get ra tat ca cac cua so dang co
	      Set<String> allWindows = driver.getWindowHandles();
	      // Dung vong lap duyet qua tat ca cac ID
	      for (String runWindows : allWindows) {
	    	  
	    	  		// Switch qua tung cua so truoc sau do ms kiem tra
	                  driver.switchTo().window(runWindows);
	                // Get ra title cua page moi switch
	                  String actualTitle = driver.getTitle();
	                // Kiem tra neu title cua page = title truyen vao
	                  if (actualTitle.equals(expectedTitle)) {
	                // Thoat khoi vong lap- ko kiem tra nhung thang khac nua
	                     break;
	                  }
	      }
	  }
	  
	  public void closeAllWindowsWithoutParentWindows(String parentWindowID) {
		  // Get ra tat ca cac ID cua cac cua so
	      Set<String> allWindows = driver.getWindowHandles();
	      // Dung vong lap for de duyet qua tung ID
	      for (String runWindows : allWindows) {
	    	  // Neu ID khong bang parentID 
	                  if (!runWindows.equals(parentWindowID)) {
	                	  // No se switch qua
	                              driver.switchTo().window(runWindows);
	                              // Dong tab hien tai
	                              driver.close();
	                  }
	      }
	      // Chi con lai 1 cua so duy nhat (parent)
	      driver.switchTo().window(parentWindowID);
	  }
	  
	  public void clickElementByJavascript(WebElement element) {
		    JavascriptExecutor je = (JavascriptExecutor) driver;
		    je.executeScript("arguments[0].click();", element);
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
