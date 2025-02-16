package com.qa.opencart.base;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;

	protected Properties prop;
	protected CommonsPage commonsPage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;

	@BeforeTest
	public void setup() {
		ChainPluginService.getInstance().addSystemInfo("Build#","1.0");
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		commonsPage = new CommonsPage(driver);
	}

	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
//			ChainTestListener.embed(DriverFactory.getScreenShotFile(), "image/png");
			ChainTestListener.embed(DriverFactory.getScreenShotByte(), "image/png");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
