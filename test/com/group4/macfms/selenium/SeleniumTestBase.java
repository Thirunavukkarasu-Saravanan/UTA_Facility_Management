package com.group4.macfms.selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

import com.group4.macfms.selenium.functions.SeleniumFunctionsBase;

public class SeleniumTestBase extends SeleniumFunctionsBase{

	public static String sAppURL, sSharedUIMapPath;
	
		public static void setDriver() throws Exception {
			
			// MAGIC CODE GOES HERE
			System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
			driver = new FirefoxDriver();
			prop = new Properties();

			// Load Configuration file
			prop.load(new FileInputStream("./config/UTAFMSApp_Config.properties"));
			sAppURL = prop.getProperty("sAppURL");
			sSharedUIMapPath = prop.getProperty("sSharedUIMapPath");

			// Load Shared UI Map
			prop.load(new FileInputStream(sSharedUIMapPath));
			driver.get(sAppURL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}