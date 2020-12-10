package com.group4.macfms.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
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
public class SeleniumTC01 extends SeleniumTestBase {

	public static String sAppURL, sSharedUIMapPath, username, password;
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
		 String screenShotName = "TC01_Homepage";
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
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(user, screenShotName);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/studentUserSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
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
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = loginUser.loginError(userName, password, screenShotName);		
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/studentUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Problem"))).click();
	}
	
	@Test
	@FileParameters("./excel/reportMarErrorTestData.csv")
	public void e_createMarErrorValidationsTest(int testCaseNo, String facilityName, String description,
			String expectedErrorMsg) {
		String actualErrorMsg = "";	
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		actualErrorMsg = validateMarError(facilityName, description, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/reportMarSuccessTestData.csv")
	public void f_createMarSuccess(int testCaseNo, String facilityName, String description, String expectedErrorMsg) {
		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);

		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);
		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		snapShot.takeScreenshot(screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Success"))).click();
		logout();
	}

	private String validateMarError(String facilityName, String description, String screenShotName) {

		new Select(driver.findElement(By.xpath(prop.getProperty("Select_Report_FacilityName"))))
				.selectByVisibleText(facilityName);
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Report_Description"))).sendKeys(description);
		driver.findElement(By.xpath(prop.getProperty("Btn_Report_Report"))).click();	
		snapShot.takeScreenshot(screenShotName);
		String emptyError = driver.findElement(By.xpath(prop.getProperty("Txt_Report_DescriptionError")))
				.getAttribute("value");
		return emptyError;
	}

	private void logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName();
		snapShot.takeScreenshot(screenShotName);
	}
	private void logout2() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout2"))).click();
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName();
		snapShot.takeScreenshot(screenShotName);
	}
	@Test
	@FileParameters("./excel/studentUserSuccessTestLoginData.csv")
	public void g_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC05_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_ViewProfile"))).click();
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
		
	}
	@Test
	@FileParameters("./excel/updatedata.csv")
	public void h_updateErrorValidationsTest(int testCaseNo, String address, String expectedErrorMsg) {	
		String actualErrorMsg = "";
		String screenShotName = "TC05_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = validateaddressError(address, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	@Test
	@FileParameters("./excel/updatedatasuccess.csv")
	public void i_updateSuccessTest(int testCaseNo, String address) {
		String screenShotName = "TC05_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
		driver.findElement(By.xpath(".//*[@id='address']")).clear();
		driver.findElement(By.xpath(".//*[@id='address']")).sendKeys(address);
		driver.findElement(By.xpath(".//*[@id='update']")).click();
		snapShot.takeScreenshot(screenShotName);
		
		logout2();		
	}
	
	private String validateaddressError(String address, String screenShotName) {
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
		driver.findElement(By.xpath(".//*[@id='address']")).clear();
		driver.findElement(By.xpath(".//*[@id='address']")).sendKeys(address);
		driver.findElement(By.xpath(".//*[@id='update']")).click();
		
		String addressError = driver.findElement(By.xpath(".//*[@id='address_errorMessage']"))
				.getAttribute("value");
		snapShot.takeScreenshot(screenShotName);
		return addressError;
	}
	
	@Test
	@FileParameters("./excel/studentUserSuccessTestLoginData.csv")
	public void j_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC01_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		
		j_view_reported_problem();
		
		
	}

	private void j_view_reported_problem() {
		// TODO Auto-generated method stub 
		
		driver.findElement(By.xpath(prop.getProperty("Btn_Search_Reported_Problem"))).click();
		
		String screenShotName = "TC01_1"+new Throwable().getStackTrace()[0].getMethodName();		
		snapShot.takeScreenshot(screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Link_First_Reported_Problem"))).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(prop.getProperty("Btn_ReportDetails_logout"))).click();
		screenShotName = "TC01_2"+new Throwable().getStackTrace()[0].getMethodName();		
		snapShot.takeScreenshot(screenShotName);
		
		
	}


}
