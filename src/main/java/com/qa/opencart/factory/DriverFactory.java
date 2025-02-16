package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import org.openqa.selenium.io.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionManager;
	public static String highligh;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name Is : " + browserName);
		highligh = prop.getProperty("highligh");
		optionManager = new OptionsManager(prop);
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
//			driver = new ChromeDriver(optionManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
//			driver = new SafariDriver();
			break;
		default:
			System.out.println("Plz Enter the valid browser name : " + browserName);
			throw new FrameworkException(" invalid browser name");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * get driver using threadLocal
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * This method is used to init the properties from .properties file
	 * 
	 * @return
	 */

	// supply env variable using maven commandline
	// mvn clean install -Denv="qa"
	public Properties initProp() {
		String envName = System.getProperty("env");
		System.out.println("Running test suite on env : " + envName);
		FileInputStream ip = null;
		prop = new Properties();
		try {

			if (envName == null) {
				System.out.println("No Env Is passed, Hence running test suite in QA env");
				ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
					break;
				case "dev":
					ip = new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
					break;
				case "stage":
					ip = new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
					break;
				case "uat":
					ip = new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
					break;
				case "prod":
					ip = new FileInputStream(AppConstants.CONFIG_PROD_PROP_FILE_PATH);
					break;
				default:
					System.out.println("Please Pass the right Env Name...." + envName);
					throw new FrameworkException("===INVALID EVN NAME===");
				}
			}
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * Take ScreenShots Method
	 */
	public static String getScreenShot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screnshot/" + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
		} catch (Exception e) {

		}
		return path;
	}
	
	public static File getScreenShotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	
	public static byte[] getScreenShotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
		
	}
}
