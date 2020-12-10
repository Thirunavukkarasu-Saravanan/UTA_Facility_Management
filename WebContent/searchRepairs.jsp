<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
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
<%  
SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
String formattedDate = sdf.format(new Date());%>
</script>
<title>Search Assigned Repairs - UTA Mac FMS</title>
</head>
<body><br>
<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Search for Assigned Repair <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/MarController?action=viewAssignedMarsByDate" method="POST" name="get_mar_date">
				<div class="row">
				<div class="col">
					<label for="date">Choose a date</label> 
					<input type="text" name="datepicker" id="datepicker" class="form-control" placeholder=<%=formattedDate%>>
				</div>
				<div class="col">
				<br> <input value='${repairNotExist}' class="form-control" id = "repair_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>	
			<br> <input type="submit" class="btn btn-primary col-md-3" role="button" value="Search for Repairs"> 
			</form>
			
		</div>
		
	</div>
</body>

</html>