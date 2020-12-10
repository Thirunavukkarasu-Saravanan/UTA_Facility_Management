<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Add a Facility - UTA Mac FMS</title>
</head>
</head>
<body>
	<br>
	<sql:setDataSource var="dsfacility" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/uta_mac_fms?autoReconnect=true&useSSL=false"
		user="root" password="Sree@321" />
	<sql:query dataSource="${dsfacility}" var="facilityresult">
   SELECT distinct FacilityType FROM uta_mac_fms.facility;
</sql:query>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Add a new Facility <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<form action="/UTA_Mac_FMS/FacilityController?action=addFacility"
			method="POST" name="reportProblem_form">
			<div class="row">
				<div class="col">
					<label for="role">Select Facility Type</label> <select name="facility"
						id="facility" class="form-control">
						<c:forEach var="row" items="${facilityresult.rows}">
							<option value='<c:out value="${row.FacilityType}"/>'><c:out
									value="${row.FacilityType}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="fname">Enter the Facility Name</label> <input
						name="fname" maxlength="15" type="text" class="form-control"
						placeholder="Ex., BMC 1 or IVBC 6">
				</div>
				<div class="col">
					<br> <input
						value="<c:out value='${facilityNameError}'/>"
						class="form-control" id="facilityname_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="availability">Availability of this Facility </label> <select name="availability"
						class="form-control">
						<option value="Available">Available</option>
						<option value="Unavailable">Unavailable</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="interval">Maximum Interval</label> <select
						name="interval" class="form-control">
						<option value="30 min">30 Minutes</option>
						<option value="1 hour">1 Hour</option>
						<option value="2 hours">2 Hours</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="venue">Venue</label> <select name="type"
						class="form-control">
						<option value="Indoor">Indoor</option>
						<option value="Outdoor">Outdoor</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="duration">Duration (Choose 7-day if it is an
						Outdoor Facility)</label> <select name="duration" class="form-control">
						<option value="Same day">Same day</option>
						<option value="7-day">7-day</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<button type="submit" class="btn btn-primary col-md-3">Add
				Facility</button>
			<button type="reset" class="btn btn-primary col-md-3">Reset</button>
			<br> <br>
		</form>
	</div>
</body>
</html>