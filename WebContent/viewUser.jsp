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
<script type="text/javascript">
	function onPageLoad() {
		$(document).ready(function() {
			$("#profile_form :input").prop("disabled", true);
		});
	}
	function editDetails() {
		document.getElementById('username').style.background = "#e6e6e6";
		document.getElementById('username').style.color = "#666666";
		document.getElementById('firstname').disabled = false;
		document.getElementById('lastname').disabled = false;
		document.getElementById('id').disabled = false;
		document.getElementById('role').disabled = false;
		document.getElementById('role').disabled = false; 
		document.getElementById('phone').disabled = false;
		document.getElementById('email').disabled = false;
		document.getElementById('address').disabled = false;
		document.getElementById('city').disabled = false;
		document.getElementById('state').disabled = false;
		document.getElementById('zip').disabled = false;
		document.getElementById('update').disabled = false;
		document.getElementById('edit').disabled = true;
	}
</script>
<title>User Details - UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'><br>
	<div class="button-box col-lg-12 offset-md-1">
		<h1><a	class="btn btn-secondary " href='${backListPage}'>Back</a></h1>
		<h1> Profile Details for ${profile.username} <a	class="btn btn-primary offset-md-1 " href='${homePage}'>Home Page</a>
		<input id="edit" type="button"
				class="btn btn-primary offset-md-1"
				value="Edit Details" onclick="editDetails();"
				style="color: white; cursor: pointer;" />
		 <a	class="btn btn-danger offset-md-1" href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>
		<br>
		<form action="/UTA_Mac_FMS/ProfileController?action=updateUserDetailsAdmin"
			method="POST" id="profile_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username"
						id="username" value='${profile.username}' class="form-control"
						READONLY>
				</div>
				<div class="col">
					<br> <input value='${errorMsg.usernameError}' class="form-control"
						id="login_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60"> 
				</div>
			</div>
			<br>
			 <div class="row">
				<div class="col">
					<label for="passowrd">Password</label> <input name="password"
						id='password' type='password' value='${profile.password}'
						class="form-control" READONLY>
				</div>
				<div class="col">
					<%-- <br> <input value="<c:out value='${errorMessage.passwordError}'/>" class="form-control"
						id="password_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60"> --%>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="confirm">Confirm Password</label> <input name="confirm" id="confirm"
						type="password" class="form-control" value='${profile.password}'
						placeholder="Confirm Password" READONLY>
				</div>
				<div class="col">
					<br> <%-- <input value="<c:out value='${errorMessage.confirmPasswordError}'/>" class="form-control"
						id="cPassword_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60"> --%>
				</div>
			</div>
			<br>
						<div class="row">
				<div class="col">
					<label for="fname">First Name</label> <input name="fname"
						id="firstname" type="text" class="form-control"
						value='${profile.firstname}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.fnameError}'/>" class="form-control"
						id="fname_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="lname">Last Name</label> <input name="lname"
						id="lastname" type="text" class="form-control"
						value='${profile.lastname}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.lnameError}'/>" class="form-control"
						id="lname_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="name">UTA ID</label> <input name="id" id="id"
						type="text" class="form-control" value='${profile.id}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.idError}'/>" class="form-control"
						id="id_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="role">User Role</label> <select name="role" id="role" class="form-control" disabled>
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
					<label for="phone">Phone</label> <input name="phone" type="text"
						id="phone" class="form-control" value='${profile.phone}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.phoneError}'/>" class="form-control"
						id="phone_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="email">Email Address</label> <input name="email"
						id="email" type="text" class="form-control"
						value='${profile.email}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.emailError}'/>" class="form-control"
						id="email_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="address">Address</label> <input name="address"
						id="address" type="text" class="form-control"
						value='${profile.address}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.addressError}'/>" class="form-control"
						id="address_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="city">City</label> <input name="city" type="text"
						id="city" class="form-control" value='${profile.city}' disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.cityError}'/>" class="form-control"
						id="city_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="state">State</label> <select name="state" id="state" class="form-control" disabled>
						<!-- <option selected>Choose...</option> -->
						<option>${profile.state}</option>
						<option value="AL">AL</option>
						<option value="AK">AK</option>
						<option value="AR">AR</option>
						<option value="AZ">AZ</option>
						<option value="CA">CA</option>
						<option value="CO">CO</option>
						<option value="CT">CT</option>
						<option value="DC">DC</option>
						<option value="DE">DE</option>
						<option value="FL">FL</option>
						<option value="GA">GA</option>
						<option value="HI">HI</option>
						<option value="IA">IA</option>
						<option value="ID">ID</option>
						<option value="IL">IL</option>
						<option value="IN">IN</option>
						<option value="KS">KS</option>
						<option value="KY">KY</option>
						<option value="LA">LA</option>
						<option value="MA">MA</option>
						<option value="MD">MD</option>
						<option value="ME">ME</option>
						<option value="MI">MI</option>
						<option value="MN">MN</option>
						<option value="MO">MO</option>
						<option value="MS">MS</option>
						<option value="MT">MT</option>
						<option value="NC">NC</option>
						<option value="NE">NE</option>
						<option value="NH">NH</option>
						<option value="NJ">NJ</option>
						<option value="NM">NM</option>
						<option value="NV">NV</option>
						<option value="NY">NY</option>
						<option value="ND">ND</option>
						<option value="OH">OH</option>
						<option value="OK">OK</option>
						<option value="OR">OR</option>
						<option value="PA">PA</option>
						<option value="RI">RI</option>
						<option value="SC">SC</option>
						<option value="SD">SD</option>
						<option value="TN">TN</option>
						<option value="TX">TX</option>
						<option value="UT">UT</option>
						<option value="VT">VT</option>
						<option value="VA">VA</option>
						<option value="WA">WA</option>
						<option value="WI">WI</option>
						<option value="WV">WV</option>
						<option value="WY">WY</option>
					</select>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="zip">Zip Code</label> <input name="zip" id="zip"
						type="text" class="form-control" value='${profile.zipcode}'
						disabled>
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.zipCodeError}'/>" class="form-control"
						id="zip_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<input id="update" type="submit" class="btn btn-primary col-md-5"
						value="Update Details" disabled /> <br><br>
					<!-- <input id="reset" type="reset" class="btn btn-primary col-md-3" value="Reset" onClick="onPageLoad();" disabled/> -->
				</div>
			</div>
			<br>
		</form>
	</div>
</body>
</html>