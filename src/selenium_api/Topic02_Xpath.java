package selenium_api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_Xpath {
	WebDriver driver;

	@Test
	public void TestScript01_VerifyURLandTitle() {
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();;
		
		//Click back verify loginUrl
		driver.navigate().back();
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl,"http://live.guru99.com/index.php/customer/account/login/");
		
		//Click forward and verify Create account Url
		driver.navigate().forward();
		String createAccountPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAccountPageUrl,"http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TestScript02_VerifyErrorMessageWhenLoginEmptyUsernamePassword() {
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
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
	
	@Test
	public void TestScript03_VerifyErrorMessageLoginWithInvalidEmail() {
		
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		
		// Enter invalid Email
		WebElement element = driver.findElement(By.name("login[username]"));
		element.click();
		element.clear();
		element.sendKeys("123123213@1232.13");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Verify Error message
		String stringErrorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(stringErrorMessage,"Please enter a valid email address. For example johndoe@domain.com.");

	}
	
	@Test
	public void TestScript04_VerifyErrorMessageLoginWithPasswordlessthan6Char() {
		
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		
		// Enter invalid Email
		WebElement element = driver.findElement(By.name("login[username]"));
		element.click();
		element.clear();
		element.sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		// Enter invalid Email
		WebElement elementPass = driver.findElement(By.id("pass"));
		elementPass.click();
		elementPass.clear();
		elementPass.sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Verify Error message
		String stringErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(stringErrorMessage,"Please enter 6 or more characters without leading or trailing spaces.");

	}
	
	@Test
	public void TestScript05_VerifyErrorMessageLoginWithIncorrectPass() {
		
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		

		// Enter valid Email
		WebElement element = driver.findElement(By.name("login[username]"));
		element.click();
		element.clear();
		element.sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		// Enter incorrect password
		WebElement elementPass = driver.findElement(By.id("pass"));
		elementPass.click();
		elementPass.clear();
		elementPass.sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Verify Error message
		String stringErrorMessagePass = driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText();
		Assert.assertEquals(stringErrorMessagePass,"Invalid login or password.");
	}
	
	@Test
	public void TestScript06_CreateAccountSuccessfully() {
		
		//driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		
		// Click on Account button
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();;
		
		// Generate Email address
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		String stringEmail = "nttvy"+ stringDate + "@mailinator.com";
		
		// Enter value
		WebElement elementFirstname = driver.findElement(By.id("firstname"));
		elementFirstname.click();
		elementFirstname.clear();
		elementFirstname.sendKeys("Vy");
		WebElement elementMiddlename = driver.findElement(By.id("middlename"));
		elementMiddlename.click();
		elementMiddlename.clear();
		elementMiddlename.sendKeys("Thi Tuong");
		WebElement elementLastname = driver.findElement(By.id("lastname"));
		elementLastname.click();
		elementLastname.clear();
		elementLastname.sendKeys("Nguyen");
		WebElement elementEmail = driver.findElement(By.id("email_address"));
		elementEmail.click();
		elementEmail.clear();
		elementEmail.sendKeys(stringEmail);
		WebElement elementPassword = driver.findElement(By.id("password"));
		elementPassword.click();
		elementPassword.clear();
		elementPassword.sendKeys("Admin@1234");
		WebElement elementConfirmation = driver.findElement(By.id("confirmation"));
		elementConfirmation.click();
		elementConfirmation.clear();
		elementConfirmation.sendKeys("Admin@1234");
		
		// Click on Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		
		// Verify message
		String stringMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(stringMessage,"Thank you for registering with Main Website Store.");
		
		// Logout
		driver.findElement(By.xpath("//span[@class=\"label\" and contains(text(), 'Account')]")).click();
		driver.findElement(By.linkText("Log Out")).click();
	
		// Need to update
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Verify Home page
		//String homePageTitle = driver.getTitle();
		//Assert.assertEquals(homePageTitle, "Home page");
		
		
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
