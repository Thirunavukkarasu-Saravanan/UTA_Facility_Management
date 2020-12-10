package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.Facility;
import com.group4.macfms.util.SQLConnection;

public class SearchFacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public Facility searchSpecificFacilityDetails(String facilityName) throws SQLException {

		String queryString = "select * from uta_mac_fms.facility where FacilityName = '" + facilityName + "';";
		Facility facility = new Facility();

		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {

				facility.setFacility(facilityList.getString("FacilityType"));
				facility.setFacilityName(facilityList.getString("FacilityName"));
				facility.setMaxInterval(facilityList.getString("MaxInterval"));
				facility.setDuration(facilityList.getString("Duration"));
				facility.setType(facilityList.getString("Venue"));
				facility.setAvailability(facilityList.getString("Availability"));

				// usersInDB.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return facility;
	}
}
