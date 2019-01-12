package selenium_api;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	String img1 = "img1.PNG";
	String img2 = "img2.jpg";
	String img3 = "img3.PNG";
	String filePathName1 = rootFolder + "\\uploadFile\\" + img1;
	String filePathName2 = rootFolder + "\\uploadFile\\" + img2;
	String filePathName3 = rootFolder + "\\uploadFile\\" + img3;

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-quic");
		driver = new ChromeDriver(options);

		// Using Firefox Driver
		//driver = new FirefoxDriver(); driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 

		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TestScript_01_SingleUpload() throws Exception {
		navigateToPage("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement btnaddFile = driver.findElement(By.xpath("//input[@type ='file']"));
		btnaddFile.sendKeys(filePathName1);

		Thread.sleep(4000);
	}

	// @Test
	public void TestScript_02_MultipleUpload() throws Exception {
		// Works fine with latest FF version only
		navigateToPage("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement btnaddFile = driver.findElement(By.xpath("//input[@type ='file']"));
		btnaddFile.sendKeys(filePathName1 + "\n" + filePathName2 + "\n" + filePathName3);

		Thread.sleep(4000);

		// Verify files are added successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text() ='" + img3 + "']")).isDisplayed());

		// Click on Start button to Upload file
		List<WebElement> startButtons = driver
				.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement startButton : startButtons) {
			startButton.click();
			Thread.sleep(2000);
		}

		// Verify files are uploaded successfully
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@href and @title ='" + img3 + "']")).isDisplayed());

	}

	// Auto IT
	@Test
	public void TestScript_03_AutoIT() throws Exception {
		navigateToPage("http://blueimp.github.com/jQuery-File-Upload/");

		// Click to Add file button -> open file dialog
		if (driver.toString().toLowerCase().contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if (driver.toString().toLowerCase().contains("firefox")) {
			Commons.clickElementByJavascript(driver, driver.findElement(By.xpath("//input[@type='file']")));
		} else {
			driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();
		}

		Thread.sleep(3000);
		
		if (driver.toString().toLowerCase().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\chrome.exe", filePathName1 });
		} else if (driver.toString().toLowerCase().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\firefox.exe", filePathName1 });
		} else {
			Runtime.getRuntime().exec(new String[] { ".\\uploadFile\\ie.exe", filePathName1 });
		}
		Thread.sleep(5000);
		
		// Verify files are added successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + img1 + "']")).isDisplayed());

		// Click on Start button to Upload file
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));

		for (WebElement startBtn : startButtons) {
			startBtn.click();
			Thread.sleep(2000);
		}

		// Verify upload file successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@href and @title='" + img1 + "']")).isDisplayed());

	}

	//@Test
	public void TestScript_04_Robot() throws Exception {
		navigateToPage("http://blueimp.github.com/jQuery-File-Upload/");

		// Specify the file location with extension
		StringSelection select = new StringSelection(filePathName1);

		// Copy to Clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		// Click to Add file button -> open file dialog
		if (driver.toString().toLowerCase().contains("chrome")) {
			driver.findElement(By.cssSelector(".fileinput-button")).click();
		} else if (driver.toString().toLowerCase().contains("firefox")) {
			Commons.clickElementByJavascript(driver, driver.findElement(By.xpath("//input[@type='file']")));
		} else {
			driver.findElement(By.xpath("//span[contains(text(),'Add files...')]")).click();
		}
		Thread.sleep(3000);

		Robot robot = new Robot();
		Thread.sleep(1000);
		// ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// PRESS CTRL+ V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// RELEASE CTRL+ V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);

		// Verify files are added successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + img1 + "']")).isDisplayed());

		// Click on Start button to Upload file
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));

		for (WebElement startBtn : startButtons) {
			startBtn.click();
			Thread.sleep(2000);
		}

		// Verify file is uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@href and @title='" + img1 + "']")).isDisplayed());

	}

	public void TestScript_05_SingleUploadToEncodable() throws Exception {
		navigateToPage("https://encodable.com/uploaddemo/");
		

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public void navigateToPage(String a) {
		driver.get(a);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
