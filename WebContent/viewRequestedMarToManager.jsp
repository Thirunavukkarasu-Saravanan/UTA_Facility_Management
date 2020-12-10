<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function onPageLoad() {
		$(document).ready(function() {
			/* $("#updatemar :input").prop("disabled", true); */
			$("#mar_form :input").prop("disabled", true);
		});
	}
function editDetails() {
	document.getElementById('marnumber').style.background = "#e6e6e6";
	document.getElementById('marnumber').style.color = "#666666";
	document.getElementById('type').disabled = false;
	document.getElementById('rid').disabled = false;
	document.getElementById('reportedby').disabled = false;
	document.getElementById('urgency').disabled = false;
	document.getElementById('description').disabled = false;
	document.getElementById('datecreated').disabled = false;
	document.getElementById('assignedto').disabled = false;
	document.getElementById('time').disabled = false;
	document.getElementById('updatemar').disabled = false;
	document.getElementById('edit').disabled = true;
	document.getElementById('request').readonly = false;
	
	}
</script>
<script type="text/javascript">
$(function () {
    $("#datepicker").datepicker();
});
</script>
<title>MAR Details - UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'><br>
<sql:setDataSource var="dsfacility" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/uta_mac_fms?autoReconnect=true&useSSL=false"
		user="root" password="Sree@321" />
	<sql:query dataSource="${dsfacility}" var="repairerresult">
    SELECT Username FROM uta_mac_fms.schedule where Monday='Yes' and TotalMars<10;
</sql:query>
	<div class="button-box col-lg-12 offset-md-1">
	<h1><a	class="btn btn-secondary " href='${backListPage}'>Back</a></h1>
		<h1> MAR Details for ${mar.marNumber} <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		<input id="edit" type="button"
				class="btn btn-primary offset-md-1"
				value="Edit Details" onclick="editDetails();"
				style="color: white; cursor: pointer;" />
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/MarController?action=acceptDeclineRepairRequest" method="POST" name="mar_form">
				<div class="row">
					<div class="col">
						<label for="marnumber">MAR number</label> <input type="text" name="marnumber" id="marnumber" class="form-control" value='${mar.marNumber}' READONLY>
					</div>
					<div class="col"></div>
				</div> <br>
				<div class="row">
					<div class="col">
						<label for="type">Facility Type</label> <input type="text" name="type" id="type" class="form-control" value='${mar.facilityType}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="rid">Reservation ID</label> <input type="text" name="rid" id="rid" class="form-control"	value='${mar.reservationId}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="reportedby">Reported By</label> <input type="text" name="reportedby" id="reportedby" class="form-control" value='${mar.reportedBy}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
				<div class="col">
					<label for="urgency">Urgency</label> <select name="urgency" id="urgency" class="form-control" disabled>
						<option>${mar.urgency}</option>
						<option value="Unusable">Unusable</option>
						<option value="Major">Major</option>
						<option value="Medium">Medium</option>
						<option value="Minor">Minor</option>
					</select>
				</div>
				<div class="col"></div>
			</div><br>
				<div class="row">
					<div class="col">
						<label for="description">Description</label> <input type="text" name="description" id="description" class="form-control" value='${mar.description}' disabled>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="datecreated">Date Created (MM/DD/YYYY)</label> <input type="text" name="datecreated" id="datecreated" class="form-control" value='${mar.dateCreated}' READONLY>
					</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="assignedto">Requested By</label> <input type="text" name="assignedto" id="assignedto" class="form-control" value='${mar.assignedTo}' disabled>
					</div>
					<div class="col"></div>
				</div><br>
			<div class="row">
					<div class="col">
						<label for="time">Estimated Time</label> <select name="time" id="time" class="form-control" disabled>
						<option>${mar.estimatedTime}</option>
						<option value="30 Minutes">30 Minutes</option>
						<option value="1 Hour">1 Hour</option>
						<option value="Multiple Hours">Multiple Hours</option>
						<option value="1 Day">1 Day</option>
						<option value="Multiple Days">Multiple Days</option>
					</select>
				</div>
					<div class="col"></div>
				</div><br>
				<div class="row">
					<div class="col">
						<label for="status">MAR Status</label> <input type="text" name="status" id="status" class="form-control" value='${mar.marStatus}' disabled>
					</div>
					<div class="col"></div>
				</div>
				<br> 
				<div class="col">
						<label for="request">Accept / Deny</label> <select name="request" id="request" class="form-control" readonly="readonly">
						<option value="Accept">Accept</option>
						<option value="Deny">Deny</option>
					</select>
				</div>
				<input type="submit" class="btn btn-primary col-md-3" id="updatemar" role="button" value="Update MAR Details" disabled>
			</form>
		</div>
	</div>
</body>
</html>