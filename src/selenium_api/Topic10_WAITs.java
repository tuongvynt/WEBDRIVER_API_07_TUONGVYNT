package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import com.google.common.base.*;

	

public class Topic10_WAITs {

	// Variable declaration
	WebDriver driver;
	Date timeCurrent;
	

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		// Using Firefox Driver
		
		 //driver = new FirefoxDriver(); driver.manage().window().maximize();
		 //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 

		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TestScript_01_Implicit_Wait() throws Exception {
			navigateToPage("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// Define an implicit wait
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			// Click on Start button
			WebElement btnStart = driver.findElement(By.xpath("//button"));
			btnStart.click();
			
			// Verfy Hello word is displayed 
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));
		}
	//@Test
	public void TestScript_02_Explicit_Wait() {
			navigateToPage("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// Click on Start button
			WebElement btnStart = driver.findElement(By.xpath("//button"));
			btnStart.click();
			
			// Wait Loading icon visible
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='loading']/img")));
			
			// Verify result text is "Hello World!"
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

		}
	//@Test
	public void TestScript_03_Explicit_Wait() {
		navigateToPage("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Click on Start button
		WebElement btnStart = driver.findElement(By.xpath("//button"));
		btnStart.click();
		
		// Wait Hello world visible
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		
		// Verify result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText().equals("Hello World!"));

	}

	//@Test
	public void TestScript_04_Explicit_Wait() {
		
			// Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/dynamic_loading/2
			navigateToPage("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// Step 02 - Check Hello World text invisible -> hết bao nhiêu s? (khong co trong DOM)
			WebDriverWait wait = new WebDriverWait(driver, 2);
			timeCurrent = new Date();
			System.out.println("Start - Step 02 - Check Hello World text invisible: " + timeCurrent);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
			timeCurrent = new Date();
			System.out.println("End - Step 02 - Check Hello World text invisible: " + timeCurrent);
			
			// Step 03 - Check Loading invisible -> hết bao nhiêu s? (khong co trong DOM)
			timeCurrent = new Date();
			System.out.println("Start - Step 03 - Check Loading invisible: " + timeCurrent);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='loading']/img")));
			timeCurrent = new Date();
			System.out.println("End - Step 02 - Check Loading invisible: " + timeCurrent);
			
			// Step 04 - Click the Start button
			WebElement btnStart = driver.findElement(By.xpath("//button"));
			btnStart.click();
			
			// Wait Hello World visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
			
			// Step 05 - Check Loading invisible -> hết bao nhiêu s? (co trong Dom)
			timeCurrent = new Date();
			System.out.println("Start - Step 05 - Check Loading invisible:" + timeCurrent);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='loading']/img")));
			timeCurrent = new Date();
			System.out.println("End - Step 05 - Check Loading invisible: " + timeCurrent);
			

		}
	@Test
		public void TestScript_05_Explicit_Wait_DTP() {
			// Step 01 - Truy cập vào trang: http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
			navigateToPage("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
			
			// Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
			
			// Step 03 - In ra ngày đã chọn (Before AJAX call) -> hiện tại chưa chọn nên in ra = "No Selected Dates to display."		
			WebElement dateSelected = driver.findElement(By.xpath("//span[@class='label']"));
			System.out.println("dateSelected :" + dateSelected.getText());
			
			// Step 04 - Chọn ngày hiện tại (VD: 23/09/2017) (hoặc 1 ngày bất kì tương ứng trong tháng/ năm hiện tại)
			WebElement dateSelect = driver.findElement(By.xpath("//a[contains(text(),'24')]"));
			dateSelect.click();
			
			//Step 05 - Wait cho đến khi "loader ajax" không còn visible (sử dụng: invisibility) Xpath: //div[@class='raDiv']
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

			// Step 06 - Wait cho selected date = 23 được visible ((sử dụng: visibility) Xpath: //*[contains(@class,'rcSelected')]//a[text()='23']
			dateSelect = driver.findElement(By.xpath("//td[contains(@class,'rcSelected')]//a[contains(text(),'24')]"));
			wait.until(ExpectedConditions.visibilityOf(dateSelect));
			
			// Step 07 - Verify ngày đã chọn
			dateSelected = driver.findElement(By.xpath("//span[@class='label']"));
			System.out.println("dateSelected :" + dateSelected.getText());
			Assert.assertTrue(dateSelected.getText().equals("Thursday, January 24, 2019"));

		}
		//@Test
		public void TestScript_06_Fluent_Wait__CountDown() {
			
			// Step 01 - Truy cập vào trang: https://daominhdam.github.io/fluent-wait/ 
			navigateToPage("https://daominhdam.github.io/fluent-wait/");
			
			// Define an implicit wait
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			// Define an explicit wait
			WebDriverWait wait = new WebDriverWait(driver, 10);
			
			// Step 02 - Wait cho đến khi countdown time được visible (visibility)
			WebElement countdount = driver.findElement(By.xpath(".//*[@id='javascript_countdown_time']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='javascript_countdown_time']")));
			
			// Step 03 - Sử dụng Fluent wait
			// Khởi tạo Fluent wait
			new FluentWait<WebElement>(countdount)
					// Tổng time wait là 15s
					.withTimeout(15, TimeUnit.SECONDS)
					// Tần số mỗi 1s check 1 lần
					.pollingEvery(1, TimeUnit.SECONDS)
					// Nếu gặp exception là find ko thấy element sẽ bỏ qua
					.ignoring(NoSuchElementException.class)
					// Kiểm tra điều kiện
					.until(new Function<WebElement, Boolean>() {
						public Boolean apply(WebElement element) {
							// Kiểm tra điều kiện countdount = 00
							boolean flag = element.getText().endsWith("00");
							System.out.println("Time = " + element.getText());
							// return giá trị cho function apply
							return flag;
						}
					});
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
