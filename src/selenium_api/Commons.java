package selenium_api;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Commons {
		
	public static void clickElementByJavascript(WebDriver driver, WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	
	 public static String getSaltString() {
	      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	      StringBuilder salt = new StringBuilder();
	      Random rnd = new Random();
	      while (salt.length() < 10) { // length of the random string.
	          int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	          salt.append(SALTCHARS.charAt(index));
	      }
	      String saltStr = salt.toString()+ "@example.com";
	      return saltStr;

	  }
	 
	 public static int randomNumber() {
			Random random = new Random();
			int number = random.nextInt(99999);
			return number;
		}
}
