package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void productInfoSetUp() {
		homePage = loginPage.doLogin("sriramrk11@gmail.com", "demo@123");
	}
	
	@DataProvider
	public Object[][] getProducData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getProducData")
	public void productSearchHeaderTest(String searchKey, String productName) {
		searchResultPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		String actualHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualHeader, productName);
	}
	
	@DataProvider
	public Object[][] getProductImageSheetData() {
		Object productData[][]= ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return productData;
	}
	
	@Test(dataProvider = "getProductImageSheetData")
	public void productImageCountTest(String searchKey, String productName, String expectedImageCount) {
		searchResultPage = homePage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		int actualProductImageCount = productInfoPage.getProductImageCount();
		Assert.assertEquals(actualProductImageCount, Integer.parseInt(expectedImageCount));
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage = homePage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfo();
		productInfoMap.forEach((k,v) -> System.out.println(k + ":" + v));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("Extax"), "$2,000.00");
		softAssert.assertAll();
	}
}
