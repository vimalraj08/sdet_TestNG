package com.sdet.stepDefination;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sdet.actionClass.actionClass;
import com.sdet.helperClass.helperClass;
import com.sdet.pageObjectModel.OpenCart;

import io.github.bonigarcia.wdm.WebDriverManager;

public class stepDefination {
	public static WebDriver driver;
	static actionClass actionObj;
	static helperClass helperObj;

	@BeforeTest
	@Parameters("browser")
	public void setupTest(String browser) {
		if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().driverVersion("100.0.1185.29").setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().driverVersion("100.0.4896.75").setup();
			driver = new ChromeDriver();
		} else {
			System.out.println("The driver for the assigned browser is not available");
		}
		// Initializing objects
		actionObj = new actionClass();
		helperObj = new helperClass();

	}

	@Test
	@Parameters("browser")
	public void addProductToCart(String browser) throws Exception {

		for (int i = 0; i < 1; i++) {
			System.out.println(browser);
			List<String> list = new ArrayList<String>();
			list = helperObj.readExcel(i + 1, browser);
			System.out.println("List Values" + list);

			actionObj.launchPage();
			actionObj.loginPage(list.get(0), list.get(1));
			actionObj.searchProduct(list.get(2));
			actionObj.selectProduct();
			actionObj.addProductToCart();
			actionObj.logoutUser();

		}
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
