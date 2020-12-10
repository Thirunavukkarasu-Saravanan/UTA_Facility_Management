package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;
import com.group4.macfms.selenium.functions.LoginUserFunction;
import com.group4.macfms.selenium.functions.RegisterUserFunction;
import com.group4.macfms.selenium.functions.SnapshotFunction;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC04 extends SeleniumTestBase {

	public static String sAppURL, sSharedUIMapPath;
	// for distinguishing row > 1
	int r = 0;
	static LoginUserFunction loginUser;
	static RegisterUserFunction registerUser;
	static SnapshotFunction snapShot;
	static SeleniumTestBase seleniumTestBase;

	// Add this for Jenkins to get rid of the
	// org.openqa.selenium.remote.ProtocolHandshake createSession
	// which causes it to report the test as failed due to "error"
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
		 seleniumTestBase = new SeleniumTestBase();
		 loginUser = new LoginUserFunction();
		 registerUser = new RegisterUserFunction();
		 snapShot = new SnapshotFunction();
		 setDriver();
		 String screenShotName = "TC04_Homepage";
		 snapShot.takeScreenshot(screenShotName);
		 driver.findElement(By.xpath(prop.getProperty("Btn_Home_Register"))).click();
	}
	
	@AfterClass
	public static void tearDownAfterTest() throws Exception {
		driver.close();
	}

	@Test
	@FileParameters("./excel/allUserErrorTestRegisterData.csv")
	public void a_registerErrorValidationsTest(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String commonErrorMsg, String usernameErr, String passwordErr,
			String confirmPwdErr, String firstnameErr, String lastnameErr, String utaidErr, String phoneErr,
			String emailErr, String addressErr, String cityErr, String zipErr) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		UserErrorMsgs expectedErrorMsg = new UserErrorMsgs();
		expectedErrorMsg.setUsernameError(usernameErr);
		expectedErrorMsg.setPasswordError(passwordErr);
		expectedErrorMsg.setConfirmPasswordError(confirmPwdErr);
		expectedErrorMsg.setFnameError(firstnameErr);
		expectedErrorMsg.setLnameError(lastnameErr);
		expectedErrorMsg.setIdError(utaidErr);
		expectedErrorMsg.setPhoneError(phoneErr);
		expectedErrorMsg.setEmailError(emailErr);
		expectedErrorMsg.setAddressError(addressErr);
		expectedErrorMsg.setCityError(cityErr);
		expectedErrorMsg.setZipCodeError(zipErr);
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(user, screenShotName);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/repairerUserSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		registerUser.registerUserSuccess(user, screenShotName);
	}

	private void validateRegisterErrMsgs(UserErrorMsgs expectedErrorMsg, UserErrorMsgs actualErrorMsg) {
		assertEquals(expectedErrorMsg.getUsernameError(), actualErrorMsg.getUsernameError());
		assertEquals(expectedErrorMsg.getPasswordError(), actualErrorMsg.getPasswordError());
		assertEquals(expectedErrorMsg.getConfirmPasswordError(), actualErrorMsg.getConfirmPasswordError());
		assertEquals(expectedErrorMsg.getFnameError(), actualErrorMsg.getFnameError());
		assertEquals(expectedErrorMsg.getLnameError(), actualErrorMsg.getLnameError());
		assertEquals(expectedErrorMsg.getIdError(), actualErrorMsg.getIdError());
		assertEquals(expectedErrorMsg.getPhoneError(), actualErrorMsg.getPhoneError());
		assertEquals(expectedErrorMsg.getEmailError(), actualErrorMsg.getEmailError());
		assertEquals(expectedErrorMsg.getAddressError(), actualErrorMsg.getAddressError());
		assertEquals(expectedErrorMsg.getCityError(), actualErrorMsg.getCityError());
		assertEquals(expectedErrorMsg.getZipCodeError(), actualErrorMsg.getZipCodeError());
	}

	@Test
	@FileParameters("./excel/allUserErrorTestLoginData.csv")
	public void c_loginErrorValidationsTest(int testCaseNo, String userName, String password, String expectedErrorMsg)
			throws Exception {
		String actualErrorMsg = "";
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = loginUser.loginError(userName, password, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/repairerUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ViewRepairs"))).click();
		viewAssignedMarDetailsTest();
	}

	public void viewAssignedMarDetailsTest() throws InterruptedException {
		List<WebElement> rows= driver.findElement(By.xpath("html/body/div[1]/form/div")).findElements(By.tagName("tr"));
		int i=0;
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();	
		  if((rows.size())>1){
			  i++;
			  r++;
		  }
		if(i == 0)
		{
			driver.findElement(By.xpath(prop.getProperty("Btn_List_Homepage"))).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='radioMar1']")).click();	
		    driver.findElement(By.xpath(prop.getProperty("Btn_List_ViewMarDetails"))).click();	
		    snapShot.takeScreenshot(screenShotName);
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_Homepage"))).click();
		    logout();
		}
	}
	
	@Test
	@FileParameters("./excel/repairerUserSuccessTestLoginData.csv")
	public void d0_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_SearchForMar"))).click();
		requestMarDetailsTest();
	}

	public void requestMarDetailsTest() throws InterruptedException {
		List<WebElement> rows= driver.findElement(By.xpath("html/body/div[1]/form")).findElements(By.tagName("tr"));
		int i=0;
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();	
		  if((rows.size())>1){
			  i++;
			  r++;
		  }
		if(i == 0)
		{
			driver.findElement(By.xpath(prop.getProperty("Btn_List_Homepage"))).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='radioMar1']")).click();	
		    driver.findElement(By.xpath(prop.getProperty("Btn_List_ViewMarDetails"))).click();	
		    snapShot.takeScreenshot(screenShotName);
		    driver.findElement(By.xpath(prop.getProperty("Btn_MARDetails_UpdateMARDetails"))).click();
		    logout2();
		}
	}
	
	
	@Test
	@FileParameters("./excel/repairerUserSuccessTestLoginData.csv")
	public void d1_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ViewRepairs"))).click();
		editAssignedMarDetailsTest();
	}

	public void editAssignedMarDetailsTest() throws InterruptedException {
		List<WebElement> rows= driver.findElement(By.xpath("html/body/div[1]/form/div")).findElements(By.tagName("tr"));
		int i=0;
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();	
		  if((rows.size())>1){
			  i++;
			  r++;
		  }
		if(i == 0)
		{
			driver.findElement(By.xpath(prop.getProperty("Btn_List_Homepage"))).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='radioMar1']")).click();	
		    driver.findElement(By.xpath(prop.getProperty("Btn_List_ViewMarDetails"))).click();	
		    snapShot.takeScreenshot(screenShotName);
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_editdetails"))).click();
			new Select(driver.findElement(By.xpath(".//*[@id='time']"))).selectByVisibleText("30 Minutes");
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_requestmar"))).click(); 
		    logout2();
		}
	}
	
	@Test
	@FileParameters("./excel/repairerUserSuccessTestLoginData.csv")
	public void d2_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ViewRepairs"))).click();
		deleteAssignedMarDetailsTest();
	}
	
	public void deleteAssignedMarDetailsTest() throws InterruptedException {
		
		List<WebElement> rows= driver.findElement(By.xpath("html/body/div[1]/form/div")).findElements(By.tagName("tr"));
		int i=0;
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();	
		  if((rows.size())>1){
			  i++;
			  r++;
		  }
		if(i == 0)
		{
			driver.findElement(By.xpath(prop.getProperty("Btn_List_Homepage"))).click();
		}
		else
		{
			driver.findElement(By.xpath(".//*[@id='radioMar1']")).click();	
		    driver.findElement(By.xpath(prop.getProperty("Btn_List_ViewMarDetails"))).click();	
		    snapShot.takeScreenshot(screenShotName);
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_editdetails"))).click();
			new Select(driver.findElement(By.xpath(".//*[@id='request']"))).selectByVisibleText("Deny");
		    driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_requestmar"))).click(); 
		    String screenShotName_edittime = "TC04_"+new Throwable().getStackTrace()[0].getMethodName()+"_roleSuccess";	
			snapShot.takeScreenshot(screenShotName_edittime);
		    logout2();
		}
	}

	private void logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();;
		snapShot.takeScreenshot(screenShotName);
	}
	
	private void logout2() {
		driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_logout"))).click();
		String screenShotName = "TC04_"+new Throwable().getStackTrace()[0].getMethodName();;
		snapShot.takeScreenshot(screenShotName);
	}

}
