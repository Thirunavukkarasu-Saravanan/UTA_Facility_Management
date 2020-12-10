<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Facility Details - UTA Mac FMS</title>
</head>
<body><br>
<div class="button-box col-lg-12 offset-md-1">
	<h1><a	class="btn btn-secondary " href='${backFacilityListPage}'>Back</a> </h1>
		<h1> Facility Details for ${facility.facilityName}  <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/FacilityController?action=updateFacility" method="POST" name="facility_form">
				<div class="row">
					<div class="col">
						<label for="facility">Facility Type</label> <input type="text" name="facility" id="facility" class="form-control" value='${facility.facility}' READONLY>
					</div>
					<div class="col"></div>
				</div> <br>
				<div class="row">
					<div class="col">
						<label for="fname">Facility name</label> <input type="text" name="fname" id="fname" class="form-control" value='${facility.facilityName}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>

				<div class="row">
					<div class="col">
						<label for="interval">Max Interval</label> <input type="text" name="interval" id="interval" class="form-control" value='${facility.maxInterval}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="duration">Duration</label> <input type="text" name="duration" id="duration" class="form-control" value='${facility.duration}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="type">Venue</label> <input type="text" name="type" id="type" class="form-control" value='${facility.type}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="availability">Availability</label> <select name="availability" id="availability" class="form-control" 
						onChange="document.getElementById('updateavailability').disabled = true; document.getElementById('updatefacility').disabled = false;" disabled>
						<!-- <option selected>Choose...</option> -->
						<option value="${facility.availability}">${facility.availability}</option>
						<option value="Available">Available</option>
						<option value="Unavailable">Unavailable</option>
						</select>
					</div>
					<div class="col"> <br>
					<input class="btn btn-primary col-md-2" id="updateavailability" type="button" value="Update Availability" onClick="document.getElementById('availability').disabled = false;">
					</div>
				</div>
				<br> <input type="submit"
					class="btn btn-primary col-md-3" id="updatefacility" role="button" value="Update Facility Details" disabled>
			</form>
		</div>
	</div>
</body>
</html>