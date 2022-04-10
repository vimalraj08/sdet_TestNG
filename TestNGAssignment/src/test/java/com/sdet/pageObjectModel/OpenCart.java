package com.sdet.pageObjectModel;

import org.openqa.selenium.By;

import com.sdet.helperClass.helperClass;

public class OpenCart {

	// Home Page Locators
	public final By accountTab = By.xpath("//span[contains(text(),'My Account')]");
	public final By loginTab = By.linkText("Login");
	public final By userName = By.cssSelector("#input-email");
	public final By password = By.cssSelector("#input-password");
	public final By loginButton = By
			.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]");
	public final By pageHeading = By.partialLinkText("Your Store");
	public final By loginAssertString = By.xpath("//h2[contains(text(),'My Account')]");
	// Search Page
	public final By searchBar = By.xpath("//header/div[1]/div[1]/div[2]/div[1]/input[1]");
	public final By searchButton = By.xpath("//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]");
	public final By searchAssertString = By.xpath("//h2[contains(text(),'Products meeting the search criteria')]");
	public final By firstProduct = By
			.xpath("//body/div[@id='product-search']/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/h4[1]/a[1]");
	public final By addToCartButton = By.xpath("//button[@id='button-cart']");

	// Logout Page
	public final By accountTab2 = By.xpath("//a[@title='My Account']");
	public final By logout = By.xpath("//a[contains(text(),'Logout')]");
}
