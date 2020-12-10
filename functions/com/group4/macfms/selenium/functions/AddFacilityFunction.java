package com.group4.macfms.selenium.functions;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.group4.macfms.model.Facility;
import com.group4.macfms.model.FacilityErrorMsgs;



public class AddFacilityFunction extends SeleniumFunctionsBase {
	SnapshotFunction snapShot = new SnapshotFunction(); 
	
	public FacilityErrorMsgs AddFacilityError(Facility fac, String screenShotName){
	 
      driver.findElement(By.xpath(prop.getProperty("Txt_AddNewFacility_EnterFacilityName"))).clear();
	  driver.findElement(By.xpath(prop.getProperty("Txt_AddNewFacility_EnterFacilityName"))).sendKeys(fac.getFacilityName());
	
	  driver.findElement(By.xpath(prop.getProperty("Btn_AddNewFacility_AddFacility"))).click();
	  snapShot.takeScreenshot(screenShotName);
	 
	  FacilityErrorMsgs actualErrorMsgs = new FacilityErrorMsgs();
	  actualErrorMsgs.setFacilityNameError(driver.findElement(By.xpath(prop.getProperty("Txt_AddNewFacility_EnterFacilityName_Errormsg"))).getAttribute("value").toString());
	  

	  return actualErrorMsgs;
	  
	  
	}
	public void addFacilitySuccess(Facility fac, String screenShotName)
	{
		driver.findElement(By.xpath(prop.getProperty("Txt_AddNewFacility_EnterFacilityName"))).clear();
		  driver.findElement(By.xpath(prop.getProperty("Txt_AddNewFacility_EnterFacilityName"))).sendKeys(fac.getFacilityName());
		  driver.findElement(By.xpath(prop.getProperty("Btn_AddNewFacility_AddFacility"))).click();
		  
		  snapShot.takeScreenshot(screenShotName);
		  
	
	}

	private void logout() {
		
		driver.findElement(By.xpath(prop.getProperty("Btn_AddNewFacility_LogOut"))).click();
		String screenShotName = "TC06_"+new Throwable().getStackTrace()[0].getMethodName();
		snapShot.takeScreenshot(screenShotName);
	}
}
