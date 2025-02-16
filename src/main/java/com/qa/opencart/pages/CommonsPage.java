package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logo = By.className("img-responsive");
	private By footLink = By.xpath("//footer//a");

	public Boolean isLogoDisplayed() {
		return eleUtil.doIsElementDisplayed(logo);
	}

	public List<String> getFooterList() {
		List<WebElement> footerList = eleUtil.waitForElementsPresence(footLink, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Total Number of Footers : "+ footerList.size());
		List<String> footer = new ArrayList<String>();
		for(WebElement e : footerList) {
			String text = e.getText();
			footer.add(text);
		}
		return footer;
	}
	
	public boolean checkFooterLink(String footerLink) {
		return getFooterList().contains(footerLink);
	}
}
