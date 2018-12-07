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

public class Topic08_JavascriptExecutor {

	// Variable declaration
	WebDriver driver;

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
		public void TestScript_01() {
		  	navigateToPage("http://live.guru99.com/");
			String domainName = (String )executeForBrowser("return document.domain");
			Assert.assertEquals(domainName, "live.guru99.com");
			
			String homePageURL = (String )executeForBrowser("return document.URL");
			Assert.assertEquals(homePageURL, "http://live.guru99.com/");
			
			WebElement mobilePageLink = driver.findElement(By.xpath("//a[text()=\"Mobile\"]"));
			clickToElementByJS(mobilePageLink);
			
			WebElement samsungGalaxyButton = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class=\'actions\']/button"));
			clickToElementByJS(samsungGalaxyButton);
			
			String pageText = (String ) executeForBrowser("return document.documentElement.innerText");
			Assert.assertTrue(pageText.contains("Samsung Galaxy was added to your shopping cart."));
			
			WebElement privacyLink = driver.findElement(By.xpath("//a[text()=\"Privacy Policy\"]"));
			clickToElementByJS(privacyLink);
			
			String privacyTitle = (String) executeForBrowser("return document.title");
			Assert.assertEquals(privacyTitle, "Privacy Policy");
			scrollToBottomPage();
			WebElement wishListRow = driver.findElement(By.xpath("//th[text()=\"WISHLIST_CNT\"]/following-sibling::td[text()=\"The number of items in your Wishlist.\"]"));
			Assert.assertTrue(wishListRow.isDisplayed());
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			 
			String verifyDomain = (String )executeForBrowser("return document.domain");
			Assert.assertEquals(verifyDomain, "demo.guru99.com");
		}
	  @Test
	  public void TestScript_02() {
		  navigateToPage("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id=\"iframeResult\"]")));
		  
		  WebElement lastNameTxt = driver.findElement(By.xpath("//input[@name=\"lname\"]"));
		  WebElement firstNameTxt = driver.findElement(By.xpath("//input[@name=\"fname\"]"));
		  WebElement submit = driver.findElement(By.xpath("//input[@value=\"Submit\"]"));
		  
		  // Remove attribute
		  // Last name: <input type="text" name="lname" disabled><br>
		  removeAttributeInDOM(lastNameTxt, "disabled");
		  
		  sendkeyToElementByJS(firstNameTxt, "vy");
		  sendkeyToElementByJS(lastNameTxt, "nguyen");
		  
		  clickToElementByJS(submit);
		  WebElement testResult = driver.findElement(By.xpath("//h2[text()='Your input was received as:']/following-sibling::div[contains(text(),\"fname\")]"));
		  Assert.assertTrue(testResult.getText().contains("vy") && testResult.getText().contains("nguyen")); 
	}
	  
	  @Test
	  public void TestScript_03() {
		  navigateToPage("http://live.guru99.com/");
		  String firstName ="Vy";
		  String middleName = "Thi Tuong";
		  String lastName ="Nguyen";
		  String email = "vynguyen_automation_" + Commons.randomNumber()+ "@gmail.com";
		  String password="123456";
		  
		  clickToElementByJS(driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")));
		  clickToElementByJS(driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")));
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), firstName);
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='middlename']")), middleName);
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), lastName);
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), email);
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), password);
		  sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), password);
		   
		  clickToElementByJS(driver.findElement(By.xpath("//button[@title=\"Register\"]")));
		   
		  String pageText = (String ) executeForBrowser("return document.documentElement.innerText");
		  Assert.assertTrue(pageText.contains(firstName));
		  Assert.assertTrue(pageText.contains(lastName));
		  
	  }
	
	
 	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object clickToElementByJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object removeAttributeInDOM(WebElement element, String attribute) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object scrollToBottomPage() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Object navigateToUrlByJS(String url) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
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
