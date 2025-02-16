package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homePageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void homeTitleTest() {
		String title = homePage.getHomeTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE, AppError.HOME_PAGE_TITLE_NOT_FOUND);
	}

	@Test
	public void loginURLTest() {
		String url = homePage.getHomeURL();
		Assert.assertTrue(url.contains(AppConstants.HOME_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND);
	}

	@Test
	public void logoutLinkExitTest() {
		Assert.assertTrue(homePage.isLogoutLinkExist(), AppError.ELEMENT_NOT_FOUND);
	}

	@Test
	public void headerTest() {
		List<String> actualHeader = homePage.getHeaderList();
		System.out.println("Home Page Headers: ==> " + actualHeader);
	}

	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{ "macbook", 3 },
			{ "imac", 1 },
			{"samsung", 2},
			{"canon", 1},
			{"airtel", 0}
		};
	}

	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, int resultCount) {
		searchResultPage = homePage.doSearch(searchKey);
		Assert.assertEquals(searchResultPage.getProductResultCount(), resultCount);
	}
}
