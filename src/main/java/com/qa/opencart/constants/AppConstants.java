package com.qa.opencart.constants;

public class AppConstants {
	
	//Time Out Constants
	public final static int DEFAULT_TIME_OUT = 5;
	public final static int MIN_TIME_OUT = 15;
	public final static int MAX_TIME_OUT = 20;
	public final static int SHORT_TIME_OUT = 10;
	
	//Login Page Constants
	public final static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION = "account/login";
	
	//Home Page Constants
	public final static String HOME_PAGE_TITLE = "My Account";
	public final static String HOME_PAGE_URL_FRACTION = "route=account/account";
	public static final String CONFIG_PROD_PROP_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_QA_PROP_FILE_PATH = "./src/test/resources/config/qa.config.properties";
	public static final String CONFIG_DEV_PROP_FILE_PATH = "./src/test/resources/config/dev.config.properties";
	public static final String CONFIG_UAT_PROP_FILE_PATH = "./src/test/resources/config/uat.config.properties";
	public static final String CONFIG_STAGE_PROP_FILE_PATH = "./src/test/resources/config/stage.config.properties";
	
	
	// Sheet Name
	public static final String PRODUCT_SHEET_NAME = "product";
}
