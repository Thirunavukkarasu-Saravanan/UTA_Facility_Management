<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Manager - UTA Mac FMS</title>
</head>
<body><br>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>Hello, ${user.lastname},${user.firstname}!<a class="btn btn-danger offset-md-1"
			href="/UTA_Mac_FMS/LogoutController">Logout</a></h1> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/ProfileController"
			role="button">View/Modify Profile Details</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/MarController?action=viewUnassignedMars"
			role="button">Search for Unassigned MARs/Assign MAR</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/searchRepairs.jsp"
			role="button">Search for Assigned Repairs</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/searchFacility.jsp"
			role="button">Search/View Facility Details</a> <br> <br>  <!-- <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/FacilityController?action=listFacilities"
			role="button">Update Availability of Facility</a> <br> <br> --> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/addFacility.jsp"
			role="button">Add a new Facility</a> <br> <br> <a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/MarController?action=viewMars"
			role="button">View all MARs</a> <br> <br><a
			class="btn btn-primary col-md-4" href="/UTA_Mac_FMS/MarController?action=viewRequestedMars"
			role="button">View Requested MARs</a> <br> <br>
	</div>
</body>
</html>