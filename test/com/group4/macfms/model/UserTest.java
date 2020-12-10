package com.group4.macfms.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.group4.macfms.data.SearchUserDAO;
import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UserTest {

	@Test
	@FileParameters("./junitTestData/userTestLoginData.csv")
	public void validateLoginUserTest(int testno, String username, String password, String errMsg) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User();
			userTest.setUsername(username);
			userTest.setPassword(password);
			userTest.validateUser(userTest, regerrMsg);
			assertEquals(errMsg,regerrMsg.getLoginErrMsg());
	}
	@Test
	@FileParameters("./junitTestData/userPasswordTestData.csv")
	public void validateUserPasswordTest(int testno, String username, String password, String errMsg) {
		UserErrorMsgs regerrMsg  = new UserErrorMsgs();
		User userTest = new User();
		userTest.setUsername(username);
		userTest.setPassword(password);
		userTest.validateUserPassword(userTest, regerrMsg);
		assertEquals(errMsg,regerrMsg.getLoginErrMsg());
	}
	
	@Test
	@FileParameters("./junitTestData/userNameTestData.csv")
	public void validateUserNameTest(int testno, String username, String password, String errMsg) {
		UserErrorMsgs regerrMsg  = new UserErrorMsgs();
		User userTest = new User();
		userTest.setUsername(username);
		userTest.setPassword(password);
		userTest.validateUserName(userTest, regerrMsg);
		assertEquals(errMsg,regerrMsg.getLoginErrMsg());
	}
	
	@Test
	@FileParameters("./junitTestData/validateUserTestData.csv")
	public void validateUserTest(int testno, String username, String password, String confirmPass, String firstname,
			String lastname, String email, String phone, String utaid, 
			String address, String city, String state, String user_role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String cpasswordErr,
			String utaidErr, String emailErr, String phoneErr, String zipErr,
			String addErr, String cityErr) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			//User userTest = new User();
			User userTest = new User(firstname,lastname,username,password,confirmPass,utaid,user_role,phone,email,address,city,state,zip);
			/*userTest.setFirstname(firstname);
			userTest.setLastname(lastname);
			userTest.setPassword(password);
			userTest.setConfirmPassword(confirmPass);
			userTest.setPhone(phone);
			userTest.setId(utaid);
			userTest.setAddress(address);
			userTest.setEmail(email);
			userTest.setCity(city);
			userTest.setZipcode(zip);
			userTest.setState(state);
			*/
			userTest.validateUserDetails(userTest, regerrMsg);
			assertEquals(firstnameErr,regerrMsg.getFnameError());
			assertEquals(lastnameErr,regerrMsg.getLnameError());
			assertEquals(passwordErr,regerrMsg.getPasswordError());
			assertEquals(cpasswordErr,regerrMsg.getConfirmPasswordError());
			assertEquals(usernameErr,regerrMsg.getUsernameError());
			assertEquals(phoneErr,regerrMsg.getPhoneError());
			assertEquals(emailErr,regerrMsg.getEmailError());
			assertEquals(cityErr,regerrMsg.getCityError());
			assertEquals(zipErr,regerrMsg.getZipCodeError());
			assertEquals(utaidErr,regerrMsg.getIdError());
			assertEquals(addErr,regerrMsg.getAddressError());
			assertTrue(state.equals(userTest.getState()));
			assertEquals(errMsg,regerrMsg.getCommonerrorMsg());
	}
	
	@Test
	@FileParameters("./junitTestData/validateUserTestDataAdmin.csv")
	public void validateUserTestAdmin(int testno, String username, String password, String confirmPass, String firstname,
			String lastname, String email, String phone, String utaid, 
			String address, String city, String state, String user_role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String cpasswordErr,
			String utaidErr, String emailErr, String phoneErr, String zipErr,
			String addErr, String cityErr) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User(firstname,lastname,username,password,confirmPass,utaid,user_role,phone,email,address,city,state,zip);
					
			userTest.setFirstname(firstname);
			userTest.setLastname(lastname);
			userTest.setPassword(password);
			userTest.setConfirmPassword(confirmPass);
			userTest.setPhone(phone);
			userTest.setId(utaid);
			userTest.setAddress(address);
			userTest.setEmail(email);
			userTest.setCity(city);
			userTest.setRole(user_role);
			userTest.setState(state);
			userTest.setZipcode(zip);
			
			userTest.validateUserDetailsAdmin(userTest, regerrMsg);
			
			assertEquals(firstnameErr,regerrMsg.getFnameError());
			assertEquals(lastnameErr,regerrMsg.getLnameError());
			assertEquals(passwordErr,regerrMsg.getPasswordError());
			assertEquals(cpasswordErr,regerrMsg.getConfirmPasswordError());
			//assertEquals(usernameErr,regerrMsg.getUsernameError());
			assertEquals(phoneErr,regerrMsg.getPhoneError());
			assertEquals(emailErr,regerrMsg.getEmailError());
			assertEquals(cityErr,regerrMsg.getCityError());
			assertEquals(zipErr,regerrMsg.getZipCodeError());
			assertEquals(utaidErr,regerrMsg.getIdError());
			assertEquals(addErr,regerrMsg.getAddressError());
			assertEquals(errMsg,regerrMsg.getCommonerrorMsg());
	}
	
	@Test
	@FileParameters("./junitTestData/validateUserExistsAdminData.csv")
	public void validateUserExistsTestAdmin(int testno,  String lastName, String role, String errMsg) throws SQLException {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User();
			SearchUserDAO user = new SearchUserDAO();
			userTest.setLastname(lastName);
			userTest.setRole(role);
			ArrayList<User> list = new ArrayList<User>();
			if(lastName.isEmpty() && role.equalsIgnoreCase("All Users"))
			{
				list = user.searchAllUserDetails(userTest);
				
				boolean emptyString = list.isEmpty();
				if(emptyString)
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
					assertEquals(errMsg,regerrMsg.getUserNotExistError());
				}
				else
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
				}
			}
			else if(lastName.isEmpty() && !role.equalsIgnoreCase("All Users"))
			{
				list = user.searchUserWithRole(userTest);
				boolean emptyString = list.isEmpty();
				if(emptyString)
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
					assertEquals(errMsg,regerrMsg.getUserNotExistError());
				}
				else
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
				}
			}
			else if(!lastName.isEmpty() && role.equalsIgnoreCase("All Users"))
			{
				list = user.searchUserDetails(userTest);
				boolean emptyString = list.isEmpty();
				if(emptyString)
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
					assertEquals(errMsg,regerrMsg.getUserNotExistError());
				}
				else
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
				}
			}
			else
			{
				list = user.searchUserRoleDetails(userTest);
				boolean emptyString = list.isEmpty();
				if(emptyString)
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
					assertEquals(errMsg,regerrMsg.getUserNotExistError());
				}
				else
				{
					userTest.validateUserExistsAdmin(emptyString, regerrMsg);
				}
			}
			//userTest.validateUserExistsAdmin(userInDB, regerrMsg);
			//assertThat(actual, is(expected));
			assertEquals(errMsg,regerrMsg.getUserNotExistError());
	}
	
	@Test
	@FileParameters("./junitTestData/ValidateUserExistsData.csv")
	public void validateUserExistsTest(int testno, String username, String errMsg) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User();
			userTest.setUsername(username);
			userTest.validateUserExists(username, regerrMsg);
			assertEquals(errMsg,regerrMsg.getUserNotExistError());
	}
}