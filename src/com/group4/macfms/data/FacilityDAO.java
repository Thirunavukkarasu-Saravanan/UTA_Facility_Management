package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.group4.macfms.model.Facility;
import com.group4.macfms.util.SQLConnection;

public class FacilityDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<Facility> ReturnMatchingFacilitiesList(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Facility> facilitiesListInDB = new ArrayList<Facility>();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				Facility facility = new Facility();
				facility.setFacility(facilityList.getString("FacilityType"));
				facility.setFacilityName(facilityList.getString("FacilityName"));
				facility.setMaxInterval(facilityList.getString("MaxInterval"));
				facility.setDuration(facilityList.getString("Duration"));
				facility.setType(facilityList.getString("Venue"));
				facility.setAvailability(facilityList.getString("Availability"));

				facilitiesListInDB.add(facility);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facilitiesListInDB;
	}

	public int updateFacilityDetails(Facility facility) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int status = 0;
		String queryString = "UPDATE `uta_mac_fms`.`facility` SET `FacilityType` = '" + facility.getFacility()
				+ "', `FacilityName` = '" + facility.getFacilityName() + "', `MaxInterval` = '"
				+ facility.getMaxInterval() + "', `Duration` = '" + facility.getDuration() + "', `Venue` = '"
				+ facility.getType() + "', `Availability` = '" + facility.getAvailability()
				+ "' WHERE `FacilityName` = '" + facility.getFacilityName() + "';";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.err.println("Printing status..."+status);
		return status;
	}
	
	public int addFacilityDetails(Facility facility) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int status = 0;
		String queryString = "INSERT INTO `uta_mac_fms`.`facility` (`FacilityType`, `FacilityName`, `MaxInterval`, `Duration`, `Venue`, `Availability`) VALUES ('" + facility.getFacility()
				+ "','" + facility.getFacilityName() + "', '" + facility.getMaxInterval() + "', '" + facility.getDuration() + "', '" + facility.getType() + "', '" + facility.getAvailability() + "');";
		try {
			
			if(checkUniqueFacilityName(facility.getFacilityName()))
				return 0;
			
			else
			{
				stmt = conn.createStatement();
				status = stmt.executeUpdate(queryString);
				conn.commit();
			}
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.err.println("Printing status..."+status);
		return status;
	}
	
	
	public static boolean checkUniqueFacilityName(String facilityName) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean userNameExists = false;

		String sql = "SELECT * from uta_mac_fms.facility where FacilityName = '" + facilityName + "';";
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

	public static ArrayList<Facility> listFacilitiesWithType(String facility) {
		return ReturnMatchingFacilitiesList("SELECT * from uta_mac_fms.facility where FacilityType ='" + facility + "';");
	}

	public static ArrayList<Facility> listFacilitiesWithName(String facilityName) {
		return ReturnMatchingFacilitiesList(
				"SELECT * from uta_mac_fms.facility where FacilityName ='" + facilityName + "';");
	}

}
