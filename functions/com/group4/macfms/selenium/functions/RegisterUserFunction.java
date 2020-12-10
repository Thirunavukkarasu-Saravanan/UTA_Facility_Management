package com.group4.macfms.selenium.functions;


import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;

public class RegisterUserFunction extends SeleniumFunctionsBase {
	  SnapshotFunction snapShot = new SnapshotFunction();

	  public UserErrorMsgs registerUserError(User user, String screenShotName){
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).sendKeys(user.getUsername());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).sendKeys(user.getPassword());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_ConfirmPassword"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_ConfirmPassword"))).sendKeys(user.getConfirmPassword());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).sendKeys(user.getFirstname());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).sendKeys(user.getLastname());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).sendKeys(user.getId());
		  new Select(driver.findElement(By.xpath(prop.getProperty("Select_Register_UserRole")))).selectByVisibleText(user.getRole());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).sendKeys(user.getPhone());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).sendKeys(user.getEmail());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).sendKeys(user.getAddress());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).sendKeys(user.getCity());
		  new Select(driver.findElement(By.xpath(prop.getProperty("Select_Register_State")))).selectByVisibleText(user.getState());
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zipcode"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zipcode"))).sendKeys(user.getZipcode());
		  driver.findElement(By.xpath(prop.getProperty("Btn_Register_Register"))).click();

		  snapShot.takeScreenshot(screenShotName);

		  UserErrorMsgs actualErrorMsgs = new UserErrorMsgs();
		  actualErrorMsgs.setUsernameError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_UsernameError"))).getAttribute("value").toString());
		  actualErrorMsgs.setPasswordError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_PasswordError"))).getAttribute("value").toString());
		  actualErrorMsgs.setConfirmPasswordError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_ConfirmPassError"))).getAttribute("value").toString());
		  actualErrorMsgs.setFnameError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstNameError"))).getAttribute("value").toString());
		  actualErrorMsgs.setLnameError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastNameError"))).getAttribute("value").toString());
		  actualErrorMsgs.setIdError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAIDError"))).getAttribute("value").toString());
		  actualErrorMsgs.setPhoneError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_PhoneError"))).getAttribute("value").toString());
		  actualErrorMsgs.setEmailError(driver.findElement(By.xpath(prop.getProperty("TXt_Register_EmailError"))).getAttribute("value").toString());
		  actualErrorMsgs.setAddressError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_AddressError"))).getAttribute("value").toString());
		  actualErrorMsgs.setCityError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_CityError"))).getAttribute("value").toString());		
		  actualErrorMsgs.setZipCodeError(driver.findElement(By.xpath(prop.getProperty("Txt_Register_ZipcodeError"))).getAttribute("value").toString());
	
		  return actualErrorMsgs;
	
	}
	   
	  public void registerUserSuccess(User user, String screenShotName)
		{
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Username"))).sendKeys(user.getUsername());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Password"))).sendKeys(user.getPassword());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_ConfirmPassword"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_ConfirmPassword"))).sendKeys(user.getConfirmPassword());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_FirstName"))).sendKeys(user.getFirstname());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_LastName"))).sendKeys(user.getLastname());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_UTAID"))).sendKeys(user.getId());
			  new Select(driver.findElement(By.xpath(prop.getProperty("Select_Register_UserRole")))).selectByVisibleText(user.getRole());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Email"))).sendKeys(user.getEmail());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Phone"))).sendKeys(user.getPhone());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Address"))).sendKeys(user.getAddress());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_City"))).sendKeys(user.getCity());
			  new Select(driver.findElement(By.xpath(prop.getProperty("Select_Register_State")))).selectByVisibleText(user.getState());
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zipcode"))).clear();
			  driver.findElement(By.xpath(prop.getProperty("Txt_Register_Zipcode"))).sendKeys(user.getZipcode());
			  driver.findElement(By.xpath(prop.getProperty("Btn_Register_Register"))).click();
			  
			  snapShot.takeScreenshot(screenShotName);
			  
			  driver.findElement(By.xpath(prop.getProperty("Btn_Register_RegisterSuccess"))).click();
		}

}