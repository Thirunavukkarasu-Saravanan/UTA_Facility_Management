package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class LoginDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public User userCheck(String username) {
		String queryString = "SELECT * from uta_mac_fms.users where Username = '" + username + "';";
		User user = new User();

		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				user.setFirstname(userList.getString("FirstName"));
				user.setLastname(userList.getString("LastName"));
				user.setPassword(userList.getString("Password"));
				user.setRole(userList.getString("Role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public int insertUser(User register) {
		int status = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		if (register.getUsername() != null) {
			if(register.getRole().equalsIgnoreCase("Repairer"))
			{
				try {
					String sqlRepairer = "INSERT INTO `uta_mac_fms`.`schedule` (`Username`) VALUES ('"+ register.getUsername() +"');";
					stmt = conn.createStatement();
					int repairerStatus = stmt.executeUpdate(sqlRepairer);
					conn.commit();

				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
			try {
				String queryString = "INSERT INTO uta_mac_fms.users (`Username`,`Password`,`FirstName`,`LastName`,`UtaId`,`Phone`,`Email`,`Address`,`City`,`State`,`Zipcode`,`Role`) VALUES "
						+ "('" + register.getUsername() + "','" + register.getPassword() + "','"
						+ register.getFirstname() + "','" + register.getLastname() + "','" + register.getId() + "','"
						+ register.getPhone() + "','" + register.getEmail() + "','" + register.getAddress() + "','"
						+ register.getCity() + "','" + register.getState() + "','" + register.getZipcode() + "','"
						+ register.getRole() + "');";
				
				stmt = conn.createStatement();
				status = stmt.executeUpdate(queryString);
				conn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static boolean checkUniqueUsername(String username) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean userNameExists = false;

		String sql = "SELECT * from uta_mac_fms.users where Username = '" + username + "';";
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(sql);
			if (userList.next()) {
				userNameExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userNameExists;
	}
}
