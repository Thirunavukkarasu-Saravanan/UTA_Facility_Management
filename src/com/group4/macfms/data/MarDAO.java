package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.group4.macfms.model.Mar;
import com.group4.macfms.model.MarErrorMsgs;
import com.group4.macfms.util.SQLConnection;

public class MarDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static ArrayList<Mar> ReturnMatchingMarsList(String queryString) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Mar> marsListInDB = new ArrayList<Mar>();
		

		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				Mar mar = new Mar();
				mar.setMarNumber(marList.getString("MarNumber"));
				mar.setFacilityType(marList.getString("FacilityName"));
				mar.setReservationId(marList.getString("ReservationId"));
				mar.setReportedBy(marList.getString("ReportedBy"));
				mar.setUrgency(marList.getString("Urgency"));
				mar.setDescription(marList.getString("Description"));
				mar.setDateCreated(marList.getString("DateCreated"));
				mar.setAssignedTo(marList.getString("AssignedTo"));
				mar.setAssignedDate(marList.getString("AssignedDate"));
				mar.setEstimatedTime(marList.getString("EstimateRepair"));
				mar.setMarStatus(marList.getString("MarStatus"));

				marsListInDB.add(mar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return marsListInDB;
	}
	
	public int updateMarByRepairer(Mar mar) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int status = 0;
		
		String marNo = mar.getMarNumber();
		
		String updateQuery = "UPDATE `uta_mac_fms`.`mardetails` SET MarStatus = '"+mar.getMarStatus()+"', AssignedTo = '"+mar.getAssignedTo()+"' "
				+ ", EstimateRepair = '"+mar.getEstimatedTime()+"' where MarNumber = '"+mar.getMarNumber()+"';";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(updateQuery);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}
	
	
	public int requestMar(Mar mar, String username) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		MarErrorMsgs errorMsgs = new MarErrorMsgs();
		int status = 0;
		SimpleDateFormat simpleDateformat1 = new SimpleDateFormat("MM/dd/YYYY"); // the day of the week spelled out completely
        Date td = new Date();
        String todaysDate = simpleDateformat1.format(td);
        
		
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now);
        
     // validating if user exists and is available for that current day.
        boolean userAvailable = checkUniqueUsername(username, day);
        String Duration = checkVenueDuration(mar.getFacilityType());
        
        boolean canReserve = ruleCheckForDuration(Duration, mar.getDateCreated());
        
		if (userAvailable && canReserve) {
			String requestQuery = "UPDATE `uta_mac_fms`.`mardetails` SET MarStatus = 'Requested', AssignedTo = '"+username+"', AssignedDate = '"+todaysDate+"' where MarNumber = '"+mar.getMarNumber()+"';";
			try {
				stmt = conn.createStatement();
				status = stmt.executeUpdate(requestQuery);
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
			
		} else {
			errorMsgs.setAssignMarError("No such repairer exists..!!");
		}
		
		
		
		return status;
	}
	
	
	private boolean ruleCheckForDuration(String Duration, String CreatedDate) {
		// TODO Auto-generated method stub
		SimpleDateFormat sDateformat = new SimpleDateFormat("MM/dd/YYYY");
		Date today = new Date();
        String todaysDate = sDateformat.format(today);
        
		
//        Date d1 = null;
//        Date d2 = null;
        
        try {
        	Date d1  = sDateformat.parse(CreatedDate);
        	Date d2 = sDateformat.parse(todaysDate);
			
			long diff = d2.getTime() - d1.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			// 7-day, Same day
			if (Duration.equalsIgnoreCase("Same day") && diffDays==0) {
				
				return true;
			} else if(Duration.equals("7-day") && diffDays < 8) {
				return true;
			}
			else {
				
				return false;
			}
			
			
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
		
		return false;
	}

	private String checkVenueDuration(String FacilityName) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String duration = "";

		String sql = "select Duration From uta_mac_fms.facility where FacilityName = '"+FacilityName+"';";
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			if (result.next()) {
				duration = result.getString("Duration");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return duration;
		
	}
	
	// This function returns the count of MarPerDay, MarPerWeek of a Repairer
	@SuppressWarnings("null")
	public int[] getRepairerMarCount(Mar mar) {
		
		int[] repMarCounts = new int[2];
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		
		SimpleDateFormat simpleDateformat1 = new SimpleDateFormat("MM/dd/YYYY"); // the day of the week spelled out completely
        Date startDate = getWeekStartDate();
		String startOfWeek = simpleDateformat1.format(startDate);
		
		Date endDate = getWeekEndDate();
//		SimpleDateFormat simpleDateformat2 = new SimpleDateFormat("MM/dd/YYYY"); // the day of the week spelled out completely
        String endOfWeek = simpleDateformat1.format(endDate);
		
        Date today = new Date();
        String todaysDate = simpleDateformat1.format(today);

        // validation for the rule check on TotalMars < 10 (per week) and < 5 (per day)
		
		String query = "SELECT AssignedTo,\r\n" + 
				"(SELECT COUNT(MarNumber)  from uta_mac_fms.mardetails where AssignedDate between '"+startOfWeek+"' and '"+endOfWeek+"' \r\n" + 
				"	and AssignedTo = '"+mar.getAssignedTo()+"' GROUP BY AssignedTo) as TotalMarsCountPerWeek,\r\n" + 
				"(SELECT COUNT(MarNumber)  from uta_mac_fms.mardetails where AssignedDate = '"+todaysDate+"'\r\n" + 
				" and AssignedTo = '"+mar.getAssignedTo()+"' GROUP BY AssignedTo) as TotalMarsCountPerDay\r\n" + 
				" \r\n" + 
				" from uta_mac_fms.mardetails where AssignedTo = '"+mar.getAssignedTo()+"' Group By AssignedTo;";
		
		
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			int TotalMarsCountPerWeek = 0;
			int TotalMarsCountPerDay = 0;
			while(result.next()) {
				
					if(result.getString("TotalMarsCountPerDay")=="null" || result.getString("TotalMarsCountPerDay")==null) {
						TotalMarsCountPerDay = 0;
					} else {
						TotalMarsCountPerDay = Integer.parseInt(result.getString("TotalMarsCountPerDay"));
					}
					TotalMarsCountPerWeek = Integer.parseInt(result.getString("TotalMarsCountPerWeek"));
				
//				 TotalMarsCountPerWeek = Integer.parseInt(result.getString("TotalMarsCountPerWeek"));
//				 TotalMarsCountPerDay = Integer.parseInt(result.getString("TotalMarsCountPerDay"));
			}
			
			repMarCounts[0] = TotalMarsCountPerDay;
			repMarCounts[1] = TotalMarsCountPerWeek;
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return repMarCounts;
	}
	
	
	// This function finally assigns the Mar to a Repairer after Rule Checks
	public int finallyAssignMarToRepairer(Mar mar) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int status = 0;
		
		String queryString = "UPDATE `uta_mac_fms`.`mardetails` SET `MarNumber` = '" + mar.getMarNumber()
		+ "', `FacilityName` = '" + mar.getFacilityType() + "', `ReservationId` = '" + mar.getReservationId()
		+ "', `ReportedBy` = '" + mar.getReportedBy() + "', `Urgency` = '" + mar.getUrgency()
		+ "', `Description` = '" + mar.getDescription() + "', `DateCreated` = '" + mar.getDateCreated()
		+ "', `AssignedTo` = '" + mar.getAssignedTo() + "', `AssignedDate` = '" + mar.getAssignedDate()
		+ "', `EstimateRepair` = '" + mar.getEstimatedTime() + "', `MarStatus` = '" + mar.getMarStatus()
		+ "' WHERE `MarNumber` = '" + mar.getMarNumber() + "';";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
		
		
		
	}
	
	public static boolean checkUniqueUsername(String username, String day) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		MarErrorMsgs errorMsgs = new MarErrorMsgs();
		boolean userNameExists = false;

		String sql = "SELECT * from uta_mac_fms.schedule where Username = '" + username + "' and "+day+"='Yes';";
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(sql);
			if (userList.next()) {
				userNameExists = true;
			} else {
				return false;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userNameExists;
	}

	public static Date getWeekStartDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, -1);
	    }
	    return calendar.getTime();
	}

	public static Date getWeekEndDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, 1);
	    }
	    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
	    	calendar.add(Calendar.DATE, 6);
	    } else {
		    calendar.add(Calendar.DATE, -1);
	    }
	    return calendar.getTime();
	}
	
	
	public static ArrayList<Mar> listMars() {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails ORDER BY MarNumber;");
	}
	
	public static ArrayList<Mar> listRequestedMars() {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where MarStatus = 'Requested' ORDER BY MarNumber;");
	}

	public static ArrayList<Mar> listUnassignedMars() {
		return ReturnMatchingMarsList(
				" SELECT * from uta_mac_fms.mardetails where TRIM(AssignedTo) IS NULL or AssignedTo = '' or AssignedTo ='"
						+ null + "';");
	}

	public static ArrayList<Mar> listAssignedMars(String username) {
		return ReturnMatchingMarsList("SELECT * from uta_mac_fms.mardetails where AssignedTo ='"+ username +"' and MarStatus in ( 'Assigned', 'Requested' ) ORDER BY DateCreated;");
	}

	public static ArrayList<Mar> listReportedMars(String username) {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where ReportedBy ='" + username + "';");
	}
	
	public static ArrayList<Mar> viewAssignedMarsByDate(String username) {

		 return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where assignedDate ='"+username+"';");
	}
	public int insertMar(Mar mar, String userName) {
		int status = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		Random random = new Random();
		int x = random.nextInt(900) + 100;
		String marNumber = "MAR " + x;
		String reservationId = "R" + x;

		String querystring = "INSERT INTO uta_mac_fms.mardetails (`MarNumber`, `FacilityName`, `ReservationId`, `ReportedBy`,`Description`,`DateCreated`, `Urgency`, `MarStatus`) VALUES "
				+ "('" + marNumber + "','" + mar.getFacilityType() + "','" + reservationId + "', '" + userName + "', '"
				+ mar.getDescription() + "','" + mar.getDateCreated() + "', '" + mar.getUrgency() + "', '"
				+ mar.getMarStatus() + "');";
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(querystring);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
