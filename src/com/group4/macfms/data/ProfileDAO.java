package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class ProfileDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public User retrieveUserDetails(String username) {
		User user = new User();
		String queryString = "SELECT * from uta_mac_fms.users where Username = '" + username + "';";

		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				user.setUsername(userList.getString("Username"));
				user.setPassword(userList.getString("Password"));
				user.setFirstname(userList.getString("FirstName"));
				user.setLastname(userList.getString("LastName"));
				user.setId(userList.getString("UtaId"));
				user.setRole(userList.getString("Role"));
				user.setPhone(userList.getString("Phone"));
				user.setEmail(userList.getString("Email"));
				user.setAddress(userList.getString("Address"));
				user.setCity(userList.getString("City"));
				user.setState(userList.getString("State"));
				user.setZipcode(userList.getString("ZipCode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public int updateUserDetails(User updateDetails) {
		int status = 0;
		String queryString = "UPDATE `uta_mac_fms`.`users` SET `Password` = '" + updateDetails.getPassword() + "', "
				+ "`FirstName` = '" + updateDetails.getFirstname() + "', `LastName` = '" + updateDetails.getLastname()
				+ "', `UtaId` = '" + updateDetails.getId() + "', `Phone` = '" + updateDetails.getPhone() + "', "
				+ "`Email` = '" + updateDetails.getEmail() + "', `Address` = '" + updateDetails.getAddress()
				+ "', `City` = '" + updateDetails.getCity() + "', `State` = '" + updateDetails.getState() + "', "
				+ "`ZipCode` = '" + updateDetails.getZipcode() + "' WHERE `Username` = '" + updateDetails.getUsername()
				+ "';";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
