package com.group4.macfms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class FacilityTest {

	
	Facility facilty;
	FacilityErrorMsgs errorMsg;
	
	@Before
	public void setUp() throws Exception {
		facilty = new Facility();
		errorMsg = new FacilityErrorMsgs();
	}	
	
	@Test
	@FileParameters("./junitTestData/setFacilityTestData.csv")
	public void validateSetFacilityTest(int testno, String facility, String facilityName, String maxInterval, String duration,
			String type, String availability, String facilityNameErr) {
		
		facilty.setFacility(facility, facilityName, maxInterval, duration, type, availability);
		
		facilty.validatefacilityName(facilty, errorMsg);	

		assertTrue(facility.equals(facilty.getFacility()));
		assertTrue(maxInterval.equals(facilty.getMaxInterval()));	 
		assertTrue(duration.equals(facilty.getDuration()));
		assertTrue(type.equals(facilty.getType()));
		assertTrue(availability.equals(facilty.getAvailability()));
		assertEquals(facilityNameErr,errorMsg.getFacilityNameError());
		

	}

}
