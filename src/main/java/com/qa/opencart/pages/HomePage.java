package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By header = By.cssSelector("div#content > h2");
	private By searchButton = By.cssSelector("div#search button");

	public String getHomeTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home Page Title : " + title);
		return title;
	}

	public String getHomeURL() {
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home Page URL : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.doIsElementDisplayed(logoutLink);
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}

	}

	public List<String> getHeaderList() {
		List<WebElement> headerList = eleUtil.waitForElementsVisible(header, AppConstants.SHORT_TIME_OUT);
		List<String> valueHeaderList = new ArrayList<String>();
		for (WebElement header : headerList) {
			String textHeader = header.getText();
			valueHeaderList.add(textHeader);
		}
		return valueHeaderList;
	}

	public SearchResultPage doSearch(String searchKey) {
		System.out.println("Search Key : " + searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(searchField, AppConstants.DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(searchEle, searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

}
