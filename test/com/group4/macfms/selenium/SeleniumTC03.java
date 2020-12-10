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
public class SeleniumTC03 extends SeleniumTestBase {

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
		 snapShot = new SnapshotFunction();
		 registerUser = new RegisterUserFunction();
		 setDriver();
		 String screenShotName = "TC03_Homepage";
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
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		UserErrorMsgs actualErrorMsg = registerUser.registerUserError(user, screenShotName);
		validateRegisterErrMsgs(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/adminUserSuccessTestRegisterData.csv")
	public void b_registerSuccess(int testCaseNo, String userName, String password, String confirmPassword,
			String firstName, String lastName, String utaID, String role, String phone, String email, String address,
			String city, String state, String zipcode, String expectedErrorMsg) throws Exception {
		User user = new User(firstName, lastName, userName, password, confirmPassword, utaID, role, phone, email,
				address, city, state, zipcode);
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
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
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = loginUser.loginError(userName, password, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

	@Test
	@FileParameters("./excel/adminUserSuccessTestLoginData.csv")
	public void d_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchForAssignedRepairs"))).click();
	}

	@Test
	@FileParameters("./excel/searchUserAdminErrorTestData.csv")
	public void e_searchUserErrorValidationsTest(int testCaseNo, String lastName, String userRole, String expectedErrorMsg) {
		String actualErrorMsg = "";
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = validateUserError(lastName, userRole, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	
	@Test
	@FileParameters("./excel/searchUserAdminSuccessTestData.csv")
	public void e_searchUserSuccessTest(int testCaseNo, String lastName, String userRole,String address) {
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).sendKeys(lastName);		
		new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
		snapShot.takeScreenshot(screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Link_First_RadioButton"))).click();
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).sendKeys(address);
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_Update"))).click();
		String screenShotName1 = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		snapShot.takeScreenshot(screenShotName1);

		 //driver.findElement(By.xpath(prop.getProperty("Btn_ReportDetails_logout"))).click();
		 
		 
		 driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_Homepage"))).click();
		 
		 logout();
	}
	
	//new login 1
	@Test
	@FileParameters("./excel/adminUserSuccessTestLoginData.csv")
	public void g_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
			throws Exception {
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
		loginUser.loginSuccess(username, password, screenShotName);
		//driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchForAssignedRepairs"))).click();
	}
	
	//update csv for new role (lastname and role allUsers)
	@Test
	@FileParameters("./excel/f1_searchUserAdminSuccessTestData.csv")
	public void h_searchUserSuccessTest(int testCaseNo, String lastName, String userRole,String address) {
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchForAssignedRepairs"))).click();

		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).sendKeys(lastName);		
		new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
		snapShot.takeScreenshot(screenShotName);
		driver.findElement(By.xpath(prop.getProperty("Link_First_RadioButton"))).click();
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).sendKeys(address);
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_Update"))).click();
		String screenShotName1 = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		snapShot.takeScreenshot(screenShotName1);

//		 driver.findElement(By.xpath(prop.getProperty("Btn_Homepage"))).click();
//		 driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
		 driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_Homepage"))).click();
		 logout();
		 
		 //driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_Logout"))).click();
		 
		 //add this in last one 
		 //driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_SearchForMar"))).click();
	}
	
	
	
		//new login 2
		@Test
		@FileParameters("./excel/adminUserSuccessTestLoginData.csv")
		public void i_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
				throws Exception {
			String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
			loginUser.loginSuccess(username, password, screenShotName);
			driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchForAssignedRepairs"))).click();
		}
		
		
		//update csv for new role (Blank lastname and for repairer)
		@Test
		@FileParameters("./excel/f2_searchUserAdminSuccessTestData.csv")
		public void i_searchUserSuccessTest(int testCaseNo, String lastName, String userRole,String address) {
			String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
			driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).sendKeys(lastName);		
			new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
			driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
			snapShot.takeScreenshot(screenShotName);
			driver.findElement(By.xpath(prop.getProperty("Link_First_RadioButton"))).click();
			driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
			driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).sendKeys(address);
			driver.findElement(By.xpath(prop.getProperty("Btn_Profile_Update"))).click();
			String screenShotName1 = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
			snapShot.takeScreenshot(screenShotName1);

			 driver.findElement(By.xpath(prop.getProperty("Btn_MarDetails_Homepage"))).click();
			 //driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
			 
			 logout();
			 //add this in last one 
			 //driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_SearchForMar"))).click();
		}
		
		//new login 3
				@Test
				@FileParameters("./excel/adminUserSuccessTestLoginData.csv")
				public void j_loginSuccess(int testCaseNo, String username, String password, String expectedErrorMsg)
						throws Exception {
					String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;		
					loginUser.loginSuccess(username, password, screenShotName);
					driver.findElement(By.xpath(prop.getProperty("Btn_FMShome_SearchForAssignedRepairs"))).click();
				}
				
				//update csv for new role (lastname and for repairer)
				@Test
				@FileParameters("./excel/f3_searchUserAdminSuccessTestData.csv")
				public void j_searchUserSuccessTest(int testCaseNo, String lastName, String userRole,String address) {
					String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
					driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
					driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).sendKeys(lastName);		
					new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
					driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
					snapShot.takeScreenshot(screenShotName);
					driver.findElement(By.xpath(prop.getProperty("Link_First_RadioButton"))).click();
					driver.findElement(By.xpath(prop.getProperty("Btn_Profile_edit"))).click();
					driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).clear();
					driver.findElement(By.xpath(prop.getProperty("Txt_Profile_address"))).sendKeys(address);
					driver.findElement(By.xpath(prop.getProperty("Btn_Profile_Update"))).click();
					String screenShotName1 = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
					snapShot.takeScreenshot(screenShotName1);

					 driver.findElement(By.xpath(prop.getProperty("Btn_Homepage"))).click();
					 
					 //add this in last one 
					 driver.findElement(By.xpath(prop.getProperty("Btn_Repairer_SearchForMar"))).click();
				}	
		
	
	
	@Test
	@FileParameters("./excel/searchUsernameAdminErrorTestData.csv")
	public void k_searchUsernameErrorValidationsTest(int testCaseNo, String username, String expectedErrorMsg) {	
		String actualErrorMsg = "";
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		actualErrorMsg = validateUsernameError(username, screenShotName);
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	

	@Test
	@FileParameters("./excel/searchUsernameAdminSuccessTestData.csv")
	public void l_searchUsernameSuccessTest(int testCaseNo, String username, String userRole) {
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_"+testCaseNo;
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
		snapShot.takeScreenshot(screenShotName);
		new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
		driver.findElement(By.xpath(prop.getProperty("Btn_Profile_Update"))).click();
		String screenShotName1 = "TC03_"+new Throwable().getStackTrace()[0].getMethodName()+"_roleSuccess";	
		snapShot.takeScreenshot(screenShotName1);
		driver.findElement(By.xpath(prop.getProperty("Btn_Userhome"))).click();
		logout();		
	}

	private String validateUserError(String lastName, String userRole, String screenShotName) {

		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Profile_lastname"))).sendKeys(lastName);
		new Select(driver.findElement(By.xpath(prop.getProperty("Txt_Profile_userrole")))).selectByVisibleText(userRole);
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
		snapShot.takeScreenshot(screenShotName);
		String userError = driver.findElement(By.xpath(prop.getProperty("Common_Error_Msg")))
				.getAttribute("value");
		return userError;
	}
	
	private String validateUsernameError(String username, String screenShotName) {
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("Txt_Login_Username"))).sendKeys(username);
		driver.findElement(By.xpath(prop.getProperty("Btn_SearchForAssignedRepair_SearchForRepairs"))).click();
		snapShot.takeScreenshot(screenShotName);
		String usernameError = driver.findElement(By.xpath(prop.getProperty("Username_Error_Msg")))
				.getAttribute("value");
		return usernameError;
	}

	private void logout() {
		driver.findElement(By.xpath(prop.getProperty("Btn_UserHome_Logout"))).click();
		String screenShotName = "TC03_"+new Throwable().getStackTrace()[0].getMethodName();;
		snapShot.takeScreenshot(screenShotName);
	}

}
