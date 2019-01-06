package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Group_User01 {
	@Test(groups = "user")
	public void TestScript_01() {
		System.out.println("TestScript_01");
	}

	@Test(groups = "user")
	public void TestScript_02() {
		System.out.println("TestScript_02");
	}

	@Test(groups = "login")
	public void TestScript_03() {
		System.out.println("TestScript_03");
	}

	@Test(groups = "login")
	public void TestScript_04() {
		System.out.println("TestScript_04");
	}

	@Test(groups = "payment")
	public void TestScript_05() {
		System.out.println("TestScript_05");
	}

	@Test(groups = "payment")
	public void TestScript_06() {
		System.out.println("TestScript_06");
	}
	
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}

}
