package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil= new ElementUtil(driver);
	}
	// two impt this 
	//1. By locators : page object
	private By emailId = By.id("input-email");
	private By paswordField = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value=\"Login\"]");
	private By forgotPwd = By.linkText("Forgotten Password");
	
	//2. public page actions - methods {features}
	public String getLoginTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login Page Title : "+ title);
		return title;
	}
	public String getLoginURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login Page URL : "+ url);
		return url;
	}
	
	public boolean isForgotPwdExist() {
		return eleUtil.doIsElementDisplayed(forgotPwd);
	}
	
	public HomePage doLogin(String username, String password) {
		System.out.println("App creds are : ==>" + username + " : " + password);
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(paswordField, password);
		eleUtil.doClick(loginBtn);
		return new HomePage(driver);
	}
}
