package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_UploadFile {

	// Variable declaration
	WebDriver driver;
	
	String rootFolder = System.getProperty("user.dir");
	String img1 = "img1.png";
	String img2 = "img2.jpg";
	String img3 = "img3.png";
	String filePathName1 = rootFolder+"\\uploadFile\\"+ img1;
	String filePathName2 = rootFolder+"\\uploadFile\\"+ img2;
	String filePathName3 = rootFolder+"\\uploadFile\\"+ img3;

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		// Using Firefox Driver
		/*driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
		
		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
	    //driver = new InternetExplorerDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TestScript_01_SingleUpload() throws Exception {
		navigateToPage("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement btnaddFile = driver.findElement(By.xpath("//input[@type ='file']"));
		btnaddFile.sendKeys(filePathName1);
		
		Thread.sleep(4000);
	}
	
	@Test
	public void TestScript_02_MultipleUpload() throws Exception {
		navigateToPage("http://blueimp.github.io/jQuery-File-Upload/");
		  
		WebElement btnaddFile = driver.findElement(By.xpath("//input[@type ='file']"));
		btnaddFile.sendKeys(filePathName1 + "\n" + filePathName2  + "\n" + filePathName3);
		  
		Thread.sleep(4000);
		
		// Verify files are added successfully
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='"+filePathName1+"']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='"+filePathName2+"']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='"+filePathName3+"']")).isDisplayed());
	
		 // Click on Start button to Upload file
		 List <WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		 	for (WebElement startButton: startButtons) {
		 		startButton.click();
			 Thread.sleep(2000);
		 }
		 	
		 // Verify files are uploaded successfully
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + filePathName1 + "']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + filePathName2 + "']")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + filePathName3 + "']")).isDisplayed());
	
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
