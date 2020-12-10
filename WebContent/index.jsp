<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>User Login/Register - UTA Mac FMS</title>
</head>
<body><br>
	<div class="button-box col-lg-12 offset-md-1">
		<h1>Login to UTA Mac FMS</h1>
		<div>
			<form action="/UTA_Mac_FMS/LoginController?action=login"
				method="POST" name="login_form">
				<br> <div class="row">
				<div class="col">
					<label for="username">Enter your Username</label> <input name="username" id="username" type="text" class="form-control" placeholder="Username">
				</div>
				<div class="col">
				<br> <input value="<c:out value='${errorMessage}'/>" class="form-control" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">  
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="passowrd">Enter your Password</label> <input name="password" id="password"
						type="password" class="form-control" placeholder="Password">
				</div>
				<div class="col">
				<br> <input value="<c:out value='${incorrectPassword}'/>" class="form-control" id = "password_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> 
				</div>
			</div>
			<br>
					<br> <input type="submit" class="btn btn-primary col-md-3" role="button" value="Login"> 
						<input type="reset" class="btn btn-primary col-md-3" role="button" value="Clear"> <br> <br>
						<a class="btn btn-success col-md-6" href="/UTA_Mac_FMS/register.jsp" role="button">Register here!!!</a> <br> <br>
						<%-- <input value="<c:out value='${errorMessage}'/>" id = "login_errorMessage" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> --%>
			</form>
		</div>
	</div>
</body>
</html>