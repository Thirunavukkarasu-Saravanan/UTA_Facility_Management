package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.Mar;
import com.group4.macfms.util.SQLConnection;

public class SearchMarDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public Mar searchSpecificMarDetails(String marNumber) throws SQLException {

		String queryString = "select * from uta_mac_fms.mardetails where MarNumber = '" + marNumber + "';";
		Mar mar = new Mar();

		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {

				mar.setFacilityType(marList.getString("FacilityName"));
				mar.setUrgency(marList.getString("Urgency"));
				mar.setDescription(marList.getString("Description"));
				mar.setReportedBy(marList.getString("ReportedBy"));
				mar.setDateCreated(marList.getString("DateCreated"));
				mar.setMarNumber(marList.getString("MarNumber"));
				mar.setReservationId(marList.getString("ReservationId"));
				mar.setAssignedTo(marList.getString("AssignedTo"));
				mar.setAssignedDate(marList.getString("AssignedDate"));
				mar.setMarStatus(marList.getString("MarStatus"));
				mar.setEstimatedTime(marList.getString("EstimateRepair"));
				// usersInDB.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mar;
	}
}
