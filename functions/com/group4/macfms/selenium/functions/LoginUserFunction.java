package com.group4.macfms.selenium.functions;


import org.openqa.selenium.By;

public class LoginUserFunction extends SeleniumFunctionsBase {	
	SnapshotFunction snapShot = new SnapshotFunction();
	   
	   public String loginError(String sUserName, String sPassword, String screenShotName ) {
		   
			// Provide user name.
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);;

			 // Provide Password.
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);

			 // Click on Login button.
			driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
			
			snapShot.takeScreenshot(screenShotName);
			
			String emptyError = driver.findElement(By.xpath(prop.getProperty("Txt_Login_LoginError"))).getAttribute("value");
			String incorrectError = driver.findElement(By.xpath(prop.getProperty("Txt_Login_PasswordError"))).getAttribute("value");
			if (emptyError != null && !emptyError.equals("")) 
				return emptyError;
			else
				return incorrectError;
		  }	
	   
	   public void loginSuccess(String sUserName, String sPassword, String screenShotName ) {
			
		// Provide user name.
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(sUserName);;

		 // Provide Password.
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys(sPassword);

		 // Click on Login button.
		driver.findElement(By.xpath(prop.getProperty("Btn_Login_Login"))).click();
		
		snapShot.takeScreenshot(screenShotName);

		// We will put the verification of the Welcome message in the JUnit test file instead of here
	  }
}