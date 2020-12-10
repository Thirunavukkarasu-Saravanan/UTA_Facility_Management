package com.group4.macfms.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class MarTest {
	
	Mar mar;
	MarErrorMsgs errorMessage;
	
	@Before
	public void setUp() throws Exception {
		
		mar = new Mar();
		errorMessage = new MarErrorMsgs();
		
			
	}
	
	@Test
	@FileParameters("./junitTestData/addMarData.csv")
	public void validateaddMarDataTest(int testno, String description, String descriptionError ) {
		
		mar.setDescription(description);
		mar.validateDescription(mar, errorMessage);
		
		assertEquals(descriptionError,errorMessage.getDescriptionError());
		
		
		
//		mar.validateDescription(mar, errorMessage);
//		assertEquals(errorMsg,errorMessage.getDescriptionError());
	}
	
	@Test
	@FileParameters("./junitTestData/assignMarTest.csv")
	public void validateAssignMarTest(int testno, String assignedTo, String AssignedToErrorMsg, int StatusCode, String MarStatusErrorMsg, String MarStatus) {
//		Mar mar = new Mar();
//		MarErrorMsgs errorMessage=new MarErrorMsgs();
		mar.setAssignedTo(assignedTo);
		mar.setMarStatusCode(StatusCode);
		mar.validateAssignedTo(mar, errorMessage);
		mar.validateAssignedToStatus(mar, errorMessage, mar.getMarStatusCode());
		//assertTrue(type.equals(facilty.getType()));
		
		assertEquals(assignedTo,mar.getAssignedTo());
		assertEquals(StatusCode,mar.getMarStatusCode());
		
		assertTrue(MarStatus.equals(mar.getMarStatus()));
		assertEquals(AssignedToErrorMsg, errorMessage.getAssignedToError());
		assertEquals(MarStatusErrorMsg, errorMessage.getAssignMarError());
		
		
	}
	
	@Test
	@FileParameters("./junitTestData/assignMarRuleCheck.csv")
	public void validateAssignMarRuleCheckTest(int testno, String assignedTo, String assignedToErrorMessage, String Day,
			int CountPerDay, int CountPerWeek, String AssignRuleCheckError, String MarStatus) {
//		Mar mar = new Mar();
//		MarErrorMsgs errorMessage=new MarErrorMsgs();
		mar.setAssignedTo(assignedTo);
		mar.setMarCountsPerDay(CountPerDay);
		mar.setMarCountPerWeek(CountPerWeek);
		
		
		
//		mar.setMarStatusCode(StatusCode);
		mar.validateAssignedTo(mar, errorMessage);
		mar.validateAssignRuleCheck(mar, errorMessage);
		
//		mar.validateAssignedToStatus(mar, errorMessage, mar.getMarStatusCode());
		//assertTrue(type.equals(facilty.getType()));
		
		assertEquals(assignedTo,mar.getAssignedTo());
		assertEquals(CountPerDay,mar.getMarCountsPerDay());
		assertEquals(CountPerWeek,mar.getMarCountPerWeek());
		
		assertEquals(assignedToErrorMessage, errorMessage.getAssignedToError());
		assertEquals(AssignRuleCheckError, errorMessage.getAssignRuleCheckError());
//		assertEquals(MarStatusErrorMsg, errorMessage.getAssignMarError());
		assertTrue(MarStatus.equals(mar.getMarStatus()));
		
		
	}
	

	@Test
	@FileParameters("./junitTestData/setMarData.csv")
	public void validateaddMarDataTest(int testno, String marNumber, String facilityType,
			String reservationId, String reportedBy, String urgency,String description, String dateCreated, String assignedTo, 
			String assignedDate, String estimatedTime, String marStatus) {
		
		mar.setMar(marNumber, facilityType, reservationId, reportedBy, urgency,
				description, dateCreated, assignedTo, assignedDate, estimatedTime, marStatus);
		
		assertTrue(marNumber.equals(mar.getMarNumber()));
		assertTrue(description.equals(mar.getDescription()));
		assertTrue(facilityType.equals(mar.getFacilityType()));
		assertTrue(reservationId.equals(mar.getReservationId()));
		assertTrue(reportedBy.equals(mar.getReportedBy()));
		assertTrue(urgency.equals(mar.getUrgency()));
		assertTrue(dateCreated.equals(mar.getDateCreated()));
		assertTrue(assignedTo.equals(mar.getAssignedTo()));
		assertTrue(assignedDate.equals(mar.getAssignedDate()));
		assertTrue(estimatedTime.equals(mar.getEstimatedTime()));
		assertTrue(marStatus.equals(mar.getMarStatus()));
		
		
		
		
//		mar.validateDescription(mar, errorMessage);
//		assertEquals(errorMsg,errorMessage.getDescriptionError());
	}

}
