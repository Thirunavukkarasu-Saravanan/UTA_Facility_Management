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
<title>User Registration - UTA Mac FMS</title>
</head>
<body><br>
	<div class="button-box col-lg-12 offset-md-1 ">
		<h1>
			User Registration <a id="login" href="/UTA_Mac_FMS/index.jsp"
				class="btn btn-primary col-md-2 offset-md-2">Login</a>
		</h1>
		<form action="/UTA_Mac_FMS/LoginController?action=register"
			method="POST" name="registration_form">
			<div class="row">
				<div class="col">
					<label for="username">User Name</label> <input name="username"
						type="text" class="form-control" placeholder="Username">
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.usernameError}'/>" class="form-control"
						id="user_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="password">Password</label> <input name="password"
						type="password" class="form-control" placeholder="Password">
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.passwordError}'/>" class="form-control"
						id="password_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="confirm">Confirm Password</label> <input name="confirm"
						type="password" class="form-control"
						placeholder="Confirm Password">
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.confirmPasswordError}'/>" class="form-control"
						id="cPassword_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<label for="fname">First Name</label> <input name="fname"
						type="text" class="form-control" placeholder="First name">
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
						type="text" class="form-control" placeholder="Last name">
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
					<label for="name">UTA ID</label> <input name="id" type="text"
						class="form-control" placeholder="UTA ID">
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
					<label for="role">User Role</label> <select name="role"
						class="form-control">
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
					<label for="phone">Phone Number</label> <input name="phone" type="text"
						class="form-control" placeholder="Phone">
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
					<label for="username">Email Address</label> <input name="email"
						type="text" class="form-control" placeholder="Email">
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
						type="text" class="form-control" placeholder="Address">
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
						class="form-control" placeholder="City">
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
					<label for="rostatele">State</label> <select name="state"
						class="form-control">
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
					<label for="zip">Zip Code</label> <input name="zip" type="text"
						class="form-control" placeholder="Zip Code">
				</div>
				<div class="col">
					<br> <input value="<c:out value='${errorMessage.zipCodeError}'/>" class="form-control"
						id="zip_errorMessage" type="text"
						style="background-color: white; color: red; border: none; width: 800px"
						disabled="disabled" maxlength="60">
				</div>
			</div>
			<br>
			<button type="submit" class="btn btn-primary col-md-3">Register</button>
			<button type="reset" class="btn btn-primary col-md-3">Reset</button>
			<br><br>
		</form>
	</div>
</body>
</html>