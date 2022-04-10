package com.sdet.actionClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sdet.helperClass.helperClass;
import com.sdet.pageObjectModel.OpenCart;
import com.sdet.stepDefination.stepDefination;

public class actionClass extends OpenCart {
	
	static helperClass helperObj;
	static stepDefination stepObj;

	public actionClass() {
		stepObj = new stepDefination();
		helperObj = new helperClass();
	}

	public void launchPage() {
		// Launch Page
		stepObj.driver.get("https://demo.opencart.com/");
		stepObj.driver.manage().window().maximize();
		// Implicit wait
		stepObj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		stepObj.driver.findElement(accountTab).click();
		stepObj.driver.findElement(loginTab).click();
		Assert.assertEquals(stepObj.driver.findElement(pageHeading).getText(), "Your Store");

	}

	public void loginPage(String userEmail, String passwordStr) {
		// Login Page
		stepObj.driver.findElement(userName).sendKeys(userEmail);
		stepObj.driver.findElement(password).sendKeys(passwordStr);
		stepObj.driver.findElement(loginButton).click();
		Assert.assertEquals(stepObj.driver.findElement(loginAssertString).getText(), "My Account");
		stepObj.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void searchProduct(String ProductKeyword) {
		stepObj.driver.findElement(searchBar).clear();
		stepObj.driver.findElement(searchBar).sendKeys(ProductKeyword);
		stepObj.driver.findElement(searchButton).click();
		// Assert.assertEquals(driver.findElement(pageObj.searchAssertString).getText(),
		// "Products meeting the search criteria");

		String asserStr = stepObj.driver.findElement(searchAssertString).getText();

		if (asserStr.equalsIgnoreCase("Products meeting the search criteria")) {
			System.out.println("Test Case Pass");
		} else {
			System.out.println("Test Case Failed");
			try {
				helperClass.takeScreenShot("Failed");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test Case Failed to generate Screenshoot!");
			}
		}
	}

	public void selectProduct() {
		// Selecting the product and adding to cart

		stepObj.driver.findElement(firstProduct).click();
		WebDriverWait wait = new WebDriverWait(stepObj.driver, '5');
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
	}

	public void addProductToCart() throws Exception {

		stepObj.driver.findElement(addToCartButton).click();

		// Explicit wait as the button takes time to load
		Thread.sleep(10000);
		String asserStr = stepObj.driver.findElement(addToCartButton).getText();
		System.out.println(asserStr);
		if (asserStr.equalsIgnoreCase("Add to Cart")) {
			System.out.println("Test Case Pass");
			helperClass.takeScreenShot("Pass");
		} else {
			System.out.println("Test Case Failed");
			helperClass.takeScreenShot("Failed");
		}
	}

	public void logoutUser() {// Logout Page

		stepObj.driver.findElement(accountTab2).click();
		stepObj.driver.findElement(logout).click();
		Assert.assertEquals(stepObj.driver.findElement(pageHeading).getText(), "Your Store");
	}

}
