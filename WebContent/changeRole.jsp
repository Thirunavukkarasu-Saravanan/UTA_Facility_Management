<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Update User Role - UTA Mac FMS</title>
</head>
<body><br>
<div class="button-box col-lg-12 offset-md-1">
			<h1><a	class="btn btn-secondary " href='${backSearchUserPage}'>Back</a></h1>
		<h1> Update User Role for ${profile.username} <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<form action="/UTA_Mac_FMS/UserController?action=updateUserRole"
			method="POST" id="profile_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username"
						id="username" value='${profile.username}' class="form-control"
						READONLY>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="role">User Role</label> <select name="role" id="role" class="form-control" >
						<option>${profile.role}</option>
						<option value="Student/Faculty">Student/Faculty</option>
						<option value="Facility Manager">Facility Manager</option>
						<option value="Repairer">Repairer</option>
						<option value="Admin">Admin</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<input id="update" type="submit" class="btn btn-primary col-md-5"
						value="Update Role" /> <br><br>
				</div>
			</div>
			<br>
			</form>
</div>
</body>
</html>