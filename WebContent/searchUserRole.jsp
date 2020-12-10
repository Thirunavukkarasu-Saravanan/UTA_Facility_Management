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
<title>Update User Role - UTA Mac FMS</title>
</head>
<body><br>
<div class="button-box col-lg-12 offset-md-1">
		<h1>
			Search User Role <a class="btn btn-primary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<div>
			<form action="/UTA_Mac_FMS/UserRoleController" method="GET" name="get_user_role">
				<div class="row">
				<div class="col">
					<label for="username">Enter User Name</label> 
					<input type="text" name="username" id="username" class="form-control" placeholder="Username">
				</div>
				<div class="col">
				<br> <input value='${usernameNotExist}' class="form-control" id = "username_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>	
			<br> <input type="submit" class="btn btn-primary col-md-3" role="button" value="Search for User"> 
			</form>
		</div>
	</div>
</body>

</html>