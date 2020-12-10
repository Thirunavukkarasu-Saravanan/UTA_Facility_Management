<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Search for a Facility - UTA Mac FMS</title>
</head>
<body><br>
<sql:setDataSource var="dsfacility" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/uta_mac_fms?autoReconnect=true&useSSL=false"
 user="root" password="Sree@321"/>
<sql:query dataSource="${dsfacility}" var="facilityresult">
    SELECT distinct FacilityType FROM uta_mac_fms.facility;
</sql:query>
<sql:query dataSource="${dsfacility}" var="facilitynameresult">
    SELECT FacilityName FROM uta_mac_fms.facility order by FacilityName;
</sql:query>
<div class="button-box col-lg-12 offset-md-1">
<h1>Search for a Facility <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
</h1>

<form action="/UTA_Mac_FMS/FacilityController?action=listFacilitiesbyType" method="POST" name="searchfacility_form">
			<div class="row">
				<div class="col">
					<label for="ftype">Select a Facility Type</label> <select name="ftype" id="ftype" class="form-control">
						<c:forEach var="row" items="${facilityresult.rows}"> 
  						    <option value='<c:out value="${row.FacilityType}"/>'><c:out value="${row.FacilityType}"/></option>
					</c:forEach>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<%-- <div class="row">
				<div class="col">
					<label for="role">Facility Name</label> <select name="fname" id="fname" class="form-control">
					<option value="selectname">Select a Facility Name</option>
						<c:forEach var="row" items="${facilitynameresult.rows}"> 
  						    <option value='<c:out value="${row.FacilityName}"/>'><c:out value="${row.Facilityname}"/></option>
					</c:forEach>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br> --%>
			<div class="row">
				<div class="col">
					<input id="view" type="submit" class="btn btn-primary col-md-5"
						value="View Details"/>
				</div>
			</div>
			<br>
</form>
</div>
</body>
</html>