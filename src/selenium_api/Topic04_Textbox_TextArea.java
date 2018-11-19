package selenium_api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic04_Textbox_TextArea {
	
	// Variable declaration
	WebDriver driver;
	String CustomerID;
	private String NewCustomerName, NewDob, NewAdress, NewCity, NewState, NewPin, NewMobiNumber, NewEmail, NewPassword;
	private String EditAdress, EditCity, EditState, EditPin, EditMobiNumber, EditEmail;

	String HomePageUrl = "http://demo.guru99.com/v4";
	String User = "mngr163733";
	String Pass = "dygysYj";
	
    // Xpath/Locators Preparation
	By UserIDByTextbox = By.xpath("//input[@name='uid']");
	By PasswordByTextbox = By.xpath("//input[@name='password']");
	By LoginButton = By.xpath("//input[@name='btnLogin']");
	By WelcomeLine = By.xpath("//marquee[contains(text(),'Welcome')]");
	By MenuNewCustomer = By.xpath("//a[text()='New Customer']");
	By MenuEditCustomer = By.xpath("//a[text()='Edit Customer']");
	By EditCustomerID = By.xpath("//input[@name='cusid']");

	By CustomerNameByTextbox = By.xpath("//input[@name='name']");
	By CustomerGenderbyRadio = By.xpath("//input[@name='rad1' and @value ='f']");
	By DobByTd = By.xpath("//input[@name='dob']");
	By AddressByTextarea = By.xpath("//textarea[@name='addr']");
	By CityByTextbox = By.xpath("//input[@name='city']");
	By StateByTextbox = By.xpath("//input[@name='state']");
	By PinByTextbox = By.xpath("//input[@name='pinno']");
	By MobileNumberByTextbox = By.xpath("//input[@name='telephoneno']");
	By EmailByTextbox = By.xpath("//input[@name='emailid']");
	By SubmitButton = By.xpath("//input[@type='submit']");

	By RegisterdCustomerIDByTd = By.xpath("//td[text()='Customer ID']/following-sibling::td[1]");
	By RegisterdCustomerNameByTd = By.xpath("//td[text()='Customer Name']/following-sibling::td[1]");
	By RegisterdDobByTd = By.xpath("//td[text()='Birthdate']/following-sibling::td[1]");
	By RegisterdAddressByTd = By.xpath("//td[text()='Address']/following-sibling::td[1]");
	By RegisterdCityByTd = By.xpath("//td[text()='City']/following-sibling::td[1]");
	By RegisterdStateByTd = By.xpath("//td[text()='State']/following-sibling::td[1]");
	By RegisterdPinByTd = By.xpath("//td[text()='Pin']/following-sibling::td[1]");
	By RegisterdMobiNoByTd = By.xpath("//td[text()='Mobile No.']/following-sibling::td[1]");
	By RegisterdEmailByTd = By.xpath("//td[text()='Email']/following-sibling::td[1]");
	
	
	@Test
	public void TC01_NewCustomer() {
		navigateToPage(HomePageUrl);
		driver.findElement(UserIDByTextbox).sendKeys(User);
		driver.findElement(PasswordByTextbox).sendKeys(Pass);
		driver.findElement(LoginButton).click();
		Assert.assertTrue(isDisplayed(WelcomeLine));
		
		driver.findElement(MenuNewCustomer).click();

		driver.findElement(CustomerNameByTextbox).sendKeys(NewCustomerName);
		driver.findElement(DobByTd).sendKeys(NewDob);
		driver.findElement(AddressByTextarea).sendKeys(NewAdress);
		driver.findElement(CityByTextbox).sendKeys(NewCity);
		driver.findElement(StateByTextbox).sendKeys(NewState);
		driver.findElement(PinByTextbox).sendKeys(NewPin);
		driver.findElement(MobileNumberByTextbox).sendKeys(NewMobiNumber);
		driver.findElement(EmailByTextbox).sendKeys(NewEmail);
		driver.findElement(PasswordByTextbox).sendKeys(NewPassword);
		driver.findElement(SubmitButton).click();
	
		CustomerID = driver.findElement(RegisterdCustomerIDByTd).getText();

		Assert.assertEquals(driver.findElement(RegisterdCustomerNameByTd).getText(), NewCustomerName);
 		Assert.assertEquals(driver.findElement(RegisterdDobByTd).getText(), NewDob);
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), NewAdress);
		Assert.assertEquals(driver.findElement(RegisterdCityByTd).getText(), NewCity);
		Assert.assertEquals(driver.findElement(RegisterdStateByTd).getText(), NewState);
		Assert.assertEquals(driver.findElement(RegisterdPinByTd).getText(), NewPin);
		Assert.assertEquals(driver.findElement(RegisterdMobiNoByTd).getText(), NewMobiNumber);
		Assert.assertEquals(driver.findElement(RegisterdEmailByTd).getText(), NewEmail);
	}

	@Test
	public void TC02_EditCustomer() {

		driver.findElement(MenuEditCustomer).click();		
		driver.findElement(EditCustomerID).sendKeys(CustomerID);
		driver.findElement(SubmitButton).click();

		Assert.assertEquals(driver.findElement(CustomerNameByTextbox).getAttribute("value"), NewCustomerName);
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), NewAdress);

		driver.findElement(AddressByTextarea).clear();
		driver.findElement(CityByTextbox).clear();
		driver.findElement(StateByTextbox).clear();
		driver.findElement(PinByTextbox).clear();
		driver.findElement(MobileNumberByTextbox).clear();
		driver.findElement(EmailByTextbox).clear();
		
		driver.findElement(AddressByTextarea).sendKeys(EditAdress);
		driver.findElement(CityByTextbox).sendKeys(EditCity);
		driver.findElement(StateByTextbox).sendKeys(EditState);
		driver.findElement(PinByTextbox).sendKeys(EditPin);
		driver.findElement(MobileNumberByTextbox).sendKeys(EditMobiNumber);
		driver.findElement(EmailByTextbox).sendKeys(EditEmail);
		driver.findElement(SubmitButton).click();		

		Assert.assertEquals(driver.findElement(RegisterdCustomerNameByTd).getText(), NewCustomerName);
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String stringeNewDob = driver.findElement(RegisterdDobByTd).getText();
		Date dateNewDob = null;
		try {
			dateNewDob = dateFormat.parse(stringeNewDob);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		String strDate = dateFormat.format(dateNewDob);
   
        Assert.assertEquals(strDate, NewDob);
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), EditAdress);
		Assert.assertEquals(driver.findElement(RegisterdCityByTd).getText(), EditCity);
		Assert.assertEquals(driver.findElement(RegisterdStateByTd).getText(), EditState);
		Assert.assertEquals(driver.findElement(RegisterdPinByTd).getText(), EditPin);
		Assert.assertEquals(driver.findElement(RegisterdMobiNoByTd).getText(), EditMobiNumber);
		Assert.assertEquals(driver.findElement(RegisterdEmailByTd).getText(), EditEmail);		
	}


	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Test Data Preparation
		NewCustomerName = "Tuong Vy";
		NewDob = "12-30-1994";
		NewAdress = "16A2 To 2 Khu Vuc1";
		NewCity = "Ho Chi Minh";
		NewState = "Quan Tan Binh";
		NewPin = "123456";
		NewMobiNumber = "01225049876";
		NewEmail = "vynguyen" + Random_Number() + "@gmail.com";
		NewPassword = "123456";
		
		// Test Edited Data Preparation
		EditAdress = "16A/2 To2 Khu vuc 1 Phuong Hung Thanh";
		EditCity = "TP Can Tho";
		EditState = "Quan Cai Rang";
		EditPin = "234567";
		EditMobiNumber = "01224049669";
		EditEmail = "vynguyen_edited" + Random_Number() + "@gmail.com";
	}

	public boolean isDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed!");
			return false;
		}

	}

	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not selected!");
			return false;
		}

	}

	public boolean isEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled!");
			return false;
		}
	}
	
	// Generate data random	
		public int Random_Number() {
			Random random = new Random();
			int random_num = random.nextInt(9999);
			return random_num;
		}
		
		public void navigateToPage(String a) {
			driver.get(a);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
}
