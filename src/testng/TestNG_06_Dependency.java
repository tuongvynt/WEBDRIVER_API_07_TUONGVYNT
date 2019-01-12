package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_06_Dependency {
	WebDriver driver;
	@Parameters("browser") 
	@BeforeClass
	public void setupBrowser(String browserName) {
		System.out.println("Browser Name: " + browserName);
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if (browserName.equals("chromeheadless")) {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
		}
		
		driver.get("http://live.guru99.com/index.php/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Parameters({"email", "pass"})
	@Test()
	public void TC_01_LoginToSystem(String email, String password) {

		// Click to navigate to Login form
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Login
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='send2']")).click();

		// Verify User
		Assert.assertFalse(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class= 'box-content']/p[contains(.,'" + email + "')]"))				.isDisplayed());
	}
	
	@Test(dependsOnMethods ="TC_01_LoginToSystem")
	public void TC_02_Logout() {
		// Logout
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

		// Verify navigate to HomePage
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
