package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_UserInteractions {

	// Variable declaration
	WebDriver driver;
	Alert alert;
	String name = "Nguyen Thi Tuong Vy";
	By resultMessage = By.xpath("//p[@id='result']");

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TetScript01() {
		// Navigate to page
		navigateToPage("http://live.guru99.com/");
		WebElement element = driver.findElement(By.xpath("//div[@ class='footer']//a[@title='My Account']"));
		
		// Click on Account
		Commons.clickElementByJavascript(driver, element);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		WebElement elementAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		
		// Click on Create an Account
		Commons.clickElementByJavascript(driver, elementAccount);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TetScript02() {
		// Navigate to page
		navigateToPage("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		// Click to Dual-zone air conditioning
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Commons.clickElementByJavascript(driver, dualZoneCheckbox);
		Assert.assertTrue(dualZoneCheckbox.isSelected());
		
		// Uncheck
		Commons.clickElementByJavascript(driver, dualZoneCheckbox);
		
		// Verify after uncheck
		Assert.assertFalse(dualZoneCheckbox.isSelected());

	}

	@Test
	public void TetScript03 () throws InterruptedException {
		navigateToPage("http://demos.telerik.com/kendo-ui/styling/radios");

		By Petrol2d0ByRadio = By.xpath("//label[contains(text(),'2.0 Petrol')]/preceding-sibling::input");
		By Petrol1d8ByRadio = By.xpath("//label[contains(text(),'1.8 Petrol')]/preceding-sibling::input");
		WebElement Petrol2d0ByRadioElement = driver.findElement(Petrol2d0ByRadio);
		WebElement Petrol1d8ByRadioElement = driver.findElement(Petrol1d8ByRadio);
		
		// Step 02 - Click vào radiobutton:  2.0
		Commons.clickElementByJavascript(driver, Petrol2d0ByRadioElement);
		Thread.sleep(1000);
		
		//Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		Assert.assertTrue(Petrol2d0ByRadioElement.isSelected());
		
		// Uncheck 2.0
		Commons.clickElementByJavascript(driver, Petrol1d8ByRadioElement);
		
		// Verify checkbox 2.0 is unselected
		Assert.assertFalse(Petrol2d0ByRadioElement.isSelected());
		
		// Verify checkbox 1.8 is selected
		Assert.assertTrue(Petrol1d8ByRadioElement.isSelected());

	}

	@Test
	public void TetScript04 () throws InterruptedException {
		// Navigate to page
		navigateToPage("https://daominhdam.github.io/basic-form/index.html");
		
		// Click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(1000);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// Click on ok
		alert.accept();
		Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked an alert successfully");
		
	}
	@Test
	public void TetScript05 () throws InterruptedException {
		// Navigate to page
		navigateToPage("https://daominhdam.github.io/basic-form/index.html");
		
		// Click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(1000);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		// Click on Cancel
		alert.dismiss();
		
		// Verify message
		Assert.assertEquals(driver.findElement(resultMessage).getText(), "You clicked: Cancel");
		
	}
	@Test
	public void TetScript06 () throws InterruptedException {
		// Navigate to page
		navigateToPage("https://daominhdam.github.io/basic-form/index.html");
		
		// Click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(1000);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		// Enter value
		alert.sendKeys(name);
		alert.accept();
		
		// Verify message
		Assert.assertEquals(driver.findElement(resultMessage).getText(), "You entered: " + name);

	}

	@Test
	public void TetScript07 () {
		// Navigate to page
		navigateToPage("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
	}
	
	//@Test
    public void TC_07_Drag_Drop_HTML5_Xpath() throws AWTException, Exception {
		driver.get("https://html5demos.com/drag/");

		String oneXpath = "//a[@id='one']";
		String targetXpath = "//div[@id='bin']";
		
		drag_the_and_drop_html5_by_xpath(oneXpath, targetXpath);
		
		Thread.sleep(2000);
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 0 + xCentreSource;
		sourceLocation.y += 70 + yCentreSource;
		targetLocation.x += 0 + xCentreTarget;
		targetLocation.y += 70 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
