package selenium_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic04_Textbox_TextArea_DropdownList {

	// Variable declaration
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExecutor;
	String CustomerID;

	String HomePageUrl = "http://demo.guru99.com/v4";
	String User = "mngr163733";
	String Pass = "dygysYj";

	// Test Data Preparation
	String NewCustomerName = "Nguyen Thi Tuong Vy";
	String NewDob = "1994-12-30";
	String NewAdress = "123 Cong Hoa";
	String NewCity = "Ho Chi Minh";
	String NewState = "Quan Tan Binh";
	String NewPin = "123456";
	String NewMobiNumber = "01225049876";
	String NewEmail = "vynguyen" + Random_Number() + "@gmail.com";
	String NewPassword = "123456";

	// Test Edited Data Preparation
	String EditAdress = "16A2 To2 Khu vuc 1 Phuong Hung Thanh";
	String EditCity = "TP Can Tho";
	String EditState = "Quan Cai Rang";
	String EditPin = "234567";
	String EditMobiNumber = "01224049669";
	String EditEmail = "vynguyen_edited" + Random_Number() + "@gmail.com";

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

	// @Test
	public void TC01_NewCustomer() {
		// Step 1: Access vào trang: http://demo.guru99.com/v4
		navigateToPage(HomePageUrl);
		driver.findElement(UserIDByTextbox).sendKeys(User);
		driver.findElement(PasswordByTextbox).sendKeys(Pass);
		driver.findElement(LoginButton).click();

		// Step 2: Đăng nhập với thông tin
		Assert.assertTrue(isDisplayed(WelcomeLine));

		// Step 03 - Chọn menu New Customer
		driver.findElement(MenuNewCustomer).click();

		// Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
		driver.findElement(CustomerNameByTextbox).sendKeys(NewCustomerName);
		driver.findElement(CustomerGenderbyRadio).click();
		driver.findElement(DobByTd).sendKeys(NewDob);
		driver.findElement(AddressByTextarea).sendKeys(NewAdress);
		driver.findElement(CityByTextbox).sendKeys(NewCity);
		driver.findElement(StateByTextbox).sendKeys(NewState);
		driver.findElement(PinByTextbox).sendKeys(NewPin);
		driver.findElement(MobileNumberByTextbox).sendKeys(NewMobiNumber);
		driver.findElement(EmailByTextbox).sendKeys(NewEmail);
		driver.findElement(PasswordByTextbox).sendKeys(NewPassword);
		driver.findElement(SubmitButton).click();

		// Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của
		// Customer ID
		CustomerID = driver.findElement(RegisterdCustomerIDByTd).getText();

		// Step 06 - Verify tất cả thông tin được tạo mới thành công
		Assert.assertEquals(driver.findElement(RegisterdCustomerNameByTd).getText(), NewCustomerName);
		Assert.assertEquals(driver.findElement(RegisterdDobByTd).getText(), NewDob);
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), NewAdress);
		Assert.assertEquals(driver.findElement(RegisterdCityByTd).getText(), NewCity);
		Assert.assertEquals(driver.findElement(RegisterdStateByTd).getText(), NewState);
		Assert.assertEquals(driver.findElement(RegisterdPinByTd).getText(), NewPin);
		Assert.assertEquals(driver.findElement(RegisterdMobiNoByTd).getText(), NewMobiNumber);
		Assert.assertEquals(driver.findElement(RegisterdEmailByTd).getText(), NewEmail);

		// Step 07 - Chọn menu Edit Customer > Nhập Customer ID > Submit
		driver.findElement(MenuEditCustomer).click();
		driver.findElement(EditCustomerID).sendKeys(CustomerID);
		driver.findElement(SubmitButton).click();

		// Step 8: Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu
		// khi tạo mới New Customer tại Step 04
		Assert.assertEquals(driver.findElement(CustomerNameByTextbox).getAttribute("value"), NewCustomerName);
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), NewAdress);

		// Step 09 - Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị
		// disable) > Submit
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

		// Step 10 - Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit
		// thành công
		Assert.assertEquals(driver.findElement(RegisterdAddressByTd).getText(), EditAdress);
		Assert.assertEquals(driver.findElement(RegisterdCityByTd).getText(), EditCity);
		Assert.assertEquals(driver.findElement(RegisterdStateByTd).getText(), EditState);
		Assert.assertEquals(driver.findElement(RegisterdPinByTd).getText(), EditPin);
		Assert.assertEquals(driver.findElement(RegisterdMobiNoByTd).getText(), EditMobiNumber);
		Assert.assertEquals(driver.findElement(RegisterdEmailByTd).getText(), EditEmail);
	}

	// @Test
	public void TC02_VerifyHTML_DropdownList() {

		By jobRole01BySelect = By.xpath("//select[@id='job1']");

		// Step 01 - Truy cập vào trang:
		// https://daominhdam.github.io/basic-form/index.html
		navigateToPage("https://daominhdam.github.io/basic-form/index.html");

		// Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Select select = new Select(driver.findElement(jobRole01BySelect));
		Assert.assertFalse(select.isMultiple());

		// Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức
		// selectVisible
		select.selectByVisibleText("Automation Tester");

		// Step 04 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");

		// Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức
		// selectValue
		select.selectByValue("manual");

		// Step 06 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");

		// Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức
		// selectIndex
		select.selectByIndex(3);

		// Step 08 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");

		// Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(5, select.getOptions().size());
	}

	@Test
	public void TC03_VerifyCustom_DropdownList() throws InterruptedException {
		// JQuery
		navigateToPage("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//label[@for=\"number-button\" and text()=\"Select a number\"]","//span[@id='number-button']","//ul[@id=\"number-menu\"]//li[@class='ui-menu-item']/div","19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class=\"ui-selectmenu-text\" and text()='19']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//label[@for=\"number-button\" and text()=\"Select a number\"]","//span[@id='number-button']","//ul[@id=\"number-menu\"]//li[@class='ui-menu-item']/div","15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class=\"ui-selectmenu-text\" and text()='15']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//label[@for=\"number-button\" and text()=\"Select a number\"]","//span[@id='number-button']","//ul[@id=\"number-menu\"]//li[@class='ui-menu-item']/div","4");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class=\"ui-selectmenu-text\" and text()='4']")).isDisplayed());
		Thread.sleep(3000);
		
		// Kendo UI
		navigateToPage("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		
		selectItemInCustomDropdown("//label[@id='color_label']","//*[@id='cap-view']//span[@aria-owns=\"color_listbox\"]","//ul[@id='color_listbox']/li","Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='cap-view']//span[@class='k-input' and text()='Orange']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//label[@id='color_label']","//*[@id='cap-view']//span[@aria-owns=\"color_listbox\"]","//ul[@id='color_listbox']/li","Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='cap-view']//span[@class='k-input' and text()='Grey']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//label[@id='color_label']","//*[@id='cap-view']//span[@aria-owns=\"color_listbox\"]","//ul[@id='color_listbox']/li","Black");
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='cap-view']//span[@class='k-input' and text()='Black']")).isDisplayed());
		Thread.sleep(3000);
		
		// Angular 2
		navigateToPage("https://material.angular.io/components/select/examples");
		selectItemInCustomDropdown("//div[text()='Select with reset option']","//mat-select[@placeholder=\"State\"]","//mat-option/span","Washington");
		Assert.assertTrue(driver.findElement(By.xpath(".//div[@class='mat-select-value']//span[text()='Washington']")).isDisplayed());
		Thread.sleep(3000);
		
		//https://mikerodham.github.io/vue-dropdowns/
		navigateToPage("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//div[@id='app']","//div[@id='app']//div/li","//div[@id='app']//div//ul[@class=\"dropdown-menu\"]//li","First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//div//li[@class=\"dropdown-toggle\" and contains(text(),\"First Option\")]")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//div[@id='app']","//div[@id='app']//div/li","//div[@id='app']//div//ul[@class=\"dropdown-menu\"]//li","Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//div//li[@class=\"dropdown-toggle\" and contains(text(),\"Second Option\")]")).isDisplayed());
		Thread.sleep(3000);
		
		
		selectItemInCustomDropdown("//div[@id='app']","//div[@id='app']//div/li","//div[@id='app']//div//ul[@class=\"dropdown-menu\"]//li","Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//div//li[@class=\"dropdown-toggle\" and contains(text(),\"Third Option\")]")).isDisplayed());
		Thread.sleep(3000);
		
		//Editable: http://indrimuska.github.io/jquery-editable-select/
		 driver.get("http://indrimuska.github.io/jquery-editable-select/");
		 driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Ford");
		 driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
		 Thread.sleep(3000);
		 Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='default-place']/ul/li[text()=\"Ford\"]")).getAttribute("class"), "es-visible selected");
		 Thread.sleep(3000);
	}

	public void selectItemInCustomDropdown(String scrolltoXpath, String parentXpath, String childXpath, String expectedItem) {
		// Scroll to Xpath
		WebElement elementToScroll = driver.findElement(By.xpath(scrolltoXpath));
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
		
		
		// Click vào dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();


		// Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(childXpath))));

		// Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		for (WebElement child : childList) {
			String textItem = child.getText().trim();
			System.out.println("Item in dropdown - " + textItem);

			// Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp
			if (textItem.equals(expectedItem)) {
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				child.click();
				break;
			}
		}

	}

	@BeforeClass
	public void beforeClass() {
		// Using Chrome Driver
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		javaExecutor = (JavascriptExecutor) driver;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
