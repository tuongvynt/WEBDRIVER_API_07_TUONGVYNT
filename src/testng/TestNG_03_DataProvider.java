package testng;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestNG_03_DataProvider {
 
  @BeforeClass
  public void beforeClass() {
  }

  @Test(dataProvider = "dp")
  public void TestScript_01(Integer n, String s) {
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  
  
  @AfterClass
  public void afterClass() {
  }

}
