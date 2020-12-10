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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function () {
    $("#datepicker").datepicker();
});
</script>
<title>Report a Problem - UTA Mac FMS</title>
</head>
</head>
<body><br>
	<sql:setDataSource var="dsfacility" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/uta_mac_fms?autoReconnect=true&useSSL=false"
		user="root" password="Sree@321" />
	<sql:query dataSource="${dsfacility}" var="facilitynameresult">
    SELECT FacilityName FROM uta_mac_fms.facility order by FacilityName;
</sql:query>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Report a Problem <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<form action="/UTA_Mac_FMS/MarController?action=reportProblem"
			method="POST" name="reportProblem_form">
			<%-- <div class="row">
				<div class="col">
					<label for="role">Select Facility Type</label> <select name="ftype"
						id="ftype" class="form-control">
						<c:forEach var="row" items="${facilityresult.rows}">
							<option value='<c:out value="${row.FacilityType}"/>'><c:out
									value="${row.FacilityType}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br> --%>
			<div class="row">
				<div class="col">
					<label for="role">Select Facility Name received during the time of reservation</label> <select name="fname"
						id="fname" class="form-control">
						<c:forEach var="row" items="${facilitynameresult.rows}">
							<option value='<c:out value="${row.FacilityName}"/>'><c:out
									value="${row.Facilityname}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="col">
				<br> <input value="<c:out value='${facilityNameDropDownError}'/>" class="form-control" id = "facility_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>
			<br>
			<!-- <div class="row">
				<div class="col">
					<label for="urgency">Urgency</label> <select name="urgency"
						class="form-control">
						<option value="Unusable">Unusable</option>
						<option value="Major">Major</option>
						<option value="Medium">Medium</option>
						<option value="Minor">Minor</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br> -->
			<div class="row">
				<div class="col">
					<label for="description">Description</label> <input name="description"
						maxlength="200" type="text" class="form-control" placeholder="Description">
				</div>
				<div class="col">
				<br> <input value="<c:out value='${descriptionError}'/>" class="form-control" id = "description_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>
			<br>
			<!-- <div class="row">
				<div class="col">
					<label for="date">Date (MM/DD/YYYY)</label> <input name="date" type="text"  id="datepicker"
						class="form-control" placeholder="Today's Date">
                </div>
				<div class="col"></div>
			</div>
			<br> -->
			<button type="submit" class="btn btn-primary col-md-3">Report</button>
			<button type="reset" class="btn btn-primary col-md-3">Reset</button>
			<br><br>
		</form>
	</div>
</body>
</html>