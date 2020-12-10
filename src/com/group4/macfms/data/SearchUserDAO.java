package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class SearchUserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public ArrayList<User> RetrunMatchingUsersList(String queryString) throws SQLException {

		ArrayList<User> usersInDB = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);

			while (result.next()) {
				User user = new User();
				user.setUsername(result.getString("Username"));
				user.setFirstname(result.getString("FirstName"));
				user.setLastname(result.getString("LastName"));
				user.setRole(result.getString("Role"));

				usersInDB.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			stmt.close();
			conn.close();
		}
		return usersInDB;
	}

	public int updateUserRole(String username, String roleUpdate) throws SQLException {

		int status = 0;

		String query = "UPDATE uta_mac_fms.users set Role = '" + roleUpdate + "' WHERE Username = '" + username + "';";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		return status;
	}

	public ArrayList<User> searchAllUserDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select Username, FirstName, LastName, Role from uta_mac_fms.users order by Username;");
	}

	public ArrayList<User> searchUserDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select Username, FirstName, LastName, Role from uta_mac_fms.users where LastName = '"
						+ user.getLastname() + "';");
	}

	public ArrayList<User> searchUserRoleDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select Username, FirstName, LastName, Role from uta_mac_fms.users where LastName = '"
						+ user.getLastname() + "' and Role = '" + user.getRole() + "';");
	}
	
	public ArrayList<User> searchUserWithRole(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select Username, FirstName, LastName, Role from uta_mac_fms.users where Role = '" + user.getRole() + "';");
	}

	public User searchSpecificUserDetails(String username) throws SQLException {

		String queryString = "select * from uta_mac_fms.users where Username = '" + username + "';";
		User user = new User();

		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {

				user.setUsername(userList.getString("Username"));
				user.setPassword(userList.getString("Password"));
				user.setConfirmPassword(userList.getString("Password"));
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

				// usersInDB.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
