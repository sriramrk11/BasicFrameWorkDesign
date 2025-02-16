package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By productResults = By.cssSelector("div.product-thumb");

	public int getProductResultCount() {
		int resultCount = eleUtil.waitForElementsVisible(productResults, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println("Result Count ==>" + resultCount);
		return resultCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Reoduct Name : " + productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
