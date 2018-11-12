package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_WebBrowser_WebElement {
	WebDriver driver;
	By emailByTextbox = By.xpath("//input[@id='mail']");
	By educationByTextarea = By.xpath("//textarea[@id='edu']");
	By ageUnder18ByRadio = By.xpath("//input[@id='under_18']");

	By jobRole01BySelect = By.xpath("//select[@id='job1']");
	By interestsDevelopmentByCheckbox = By.xpath("//input[@id='development']");
	By silder01ByRange = By.xpath("//input[@id='slider-1']");
	By buttonIsEnabledByButton = By.xpath("//button[@id='button-enabled']");

	By passwordByTextbox = By.xpath("//input[@id='password']");
	By ageIsDisabledByRadio = By.xpath("//input[@id='radio-disabled']");
	By biographyByTextarea = By.xpath("//textarea[@id='bio']");
	By jobRole02BySelect = By.xpath("//select[@id='job2']");
	By interestsIsDisabledByCheckbox = By.xpath("//input[@id='check-disbaled']");
	By silder02ByRange = By.xpath("//input[@id='slider-2']");
	By buttonIsDisabledByButton = By.xpath("//button[@id='button-disabled']");

	@Test
	public void TestScript01_VerifyElementsAreDisplayed() {

		boolean emailByTextboxIsDisplayed = isDisplayed(emailByTextbox);
		boolean educationByTextareaIsDisplayed = isDisplayed(educationByTextarea);
		boolean ageUnder18ByRadioIsDisplayed = isDisplayed(ageUnder18ByRadio);

		Assert.assertTrue(emailByTextboxIsDisplayed);

		if (emailByTextboxIsDisplayed) {
			driver.findElement(emailByTextbox).sendKeys("Automation Testing");
		}

		if (educationByTextareaIsDisplayed) {
			driver.findElement(educationByTextarea).sendKeys("Automation Testing");
		}

		if (ageUnder18ByRadioIsDisplayed) {
			driver.findElement(ageUnder18ByRadio).click();
		}
	}

	@Test
	public void TestScript02_VerifyElementsAreEnabledOrDisabled() {

		// Check if Elements are Enabled
		Assert.assertTrue(isEnabled(emailByTextbox));
		Assert.assertTrue(isEnabled(educationByTextarea));
		Assert.assertTrue(isEnabled(ageUnder18ByRadio));
		Assert.assertTrue(isEnabled(jobRole01BySelect));
		Assert.assertTrue(isEnabled(interestsDevelopmentByCheckbox));
		Assert.assertTrue(isEnabled(silder01ByRange));
		Assert.assertTrue(isEnabled(buttonIsEnabledByButton));

		// Check if Elements are Disabled
		Assert.assertFalse(isEnabled(passwordByTextbox));
		Assert.assertFalse(isEnabled(ageIsDisabledByRadio));
		Assert.assertFalse(isEnabled(biographyByTextarea));
		Assert.assertFalse(isEnabled(jobRole02BySelect));
		Assert.assertFalse(isEnabled(interestsIsDisabledByCheckbox));
		Assert.assertFalse(isEnabled(silder02ByRange));
		Assert.assertFalse(isEnabled(buttonIsDisabledByButton));
	}

	@Test
	public void TestScript03_VerifyElementsAreSelected() {
		// Click on Elements
		driver.findElement(ageUnder18ByRadio).click();
		driver.findElement(interestsDevelopmentByCheckbox).click();

		// Check if Elements are selected, if not Click on Elements
		boolean ageUnder18ByRadioIsSelected = isSelected(ageUnder18ByRadio);
		boolean interestsDevelopmentByCheckboxIsSelected = isSelected(interestsDevelopmentByCheckbox);

		if (!ageUnder18ByRadioIsSelected) {
			driver.findElement(ageUnder18ByRadio).click();
		}

		if (!interestsDevelopmentByCheckboxIsSelected) {
			driver.findElement(interestsDevelopmentByCheckbox).click();
		}
		// Verify if Elements are selected
		Assert.assertTrue(ageUnder18ByRadioIsSelected);
		Assert.assertTrue(interestsDevelopmentByCheckboxIsSelected);
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
}
