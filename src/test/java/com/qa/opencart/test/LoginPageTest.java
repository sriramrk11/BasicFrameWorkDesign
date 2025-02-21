package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginTitleTest() {
		ChainTestListener.log("Verifying Login Page Test");
		String title = loginPage.getLoginTitle();
		Assert.assertEquals(AppConstants.LOGIN_PAGE_TITLE, "Account Login", AppError.TITLE_NOT_FOUND);
	}

	@Test(enabled = false)
	public void loginURLTest() {
		String url = loginPage.getLoginURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);
	}

	@Test
	public void forgotPwdLinkExitTest() {
		Assert.assertTrue(loginPage.isForgotPwdExist(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test(description = "Checking Log on Login Page")
	public void logoTest() {
		Assert.assertTrue(commonsPage.isLogoDisplayed(),AppError.LOGO_NOT_FOUND);
	}
	@DataProvider
	public Object[][] getFooterData() {
		return new Object[][] {
			{"About Us"},
			{"Contact Us"},
			{"Specials"},
			{"Order History"}
		};
	}
	
	@Test(dataProvider = "getFooterData", description = "Checking Footer in login Page")
	public void footerTest(String footerValue) {
		Assert.assertTrue(commonsPage.checkFooterLink(footerValue),AppError.LOGO_NOT_FOUND);
	}

	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		assertEquals(homePage.getHomeTitle(), AppConstants.HOME_PAGE_TITLE, AppError.HOME_PAGE_TITLE_NOT_FOUND);
	}

}
