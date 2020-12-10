<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Search User - UTA Mac FMS</title>
</head>
<body><br>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Search for a User <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/UserController?action=getUserDetails" method="GET" name="search_user">
				<div class="row">
				<div class="col">
					<label for="lastname">Enter Last Name (Optional)</label> 
					<input type="text" name="lastname" id="lastname" class="form-control" placeholder="Lastname">
				</div>
				<div class="col"></div>
			</div>		
			<br>
			<div class="row">
				<div class="col">
					<label for="role">User Role</label> <select name="role" id="role"
						class="form-control">
						<option value="All Users">All Users</option>
						<option value="Student/Faculty">Student/Faculty</option>
						<option value="Facility Manager">Facility Manager</option>
						<option value="Repairer">Repairer</option>
						<option value="Admin">Admin</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br> <input type="submit" class="btn btn-primary col-md-3" role="button" value="Submit"> 
			</form>
			</div>
	</div>
	<input value="<c:out value='${userNotExist}'/>" class="button-box col-lg-12 offset-md-1" id = "common_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">	
</body>
</html>