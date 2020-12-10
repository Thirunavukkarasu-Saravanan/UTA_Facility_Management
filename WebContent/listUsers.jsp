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
<!-- <script type="text/javascript">
	function onPageLoad() {
		$(document).ready(function() {
			$("#submit :input").prop("disabled", true);
		});
	}
</script> -->
<title>Users - UTA Mac FMS</title>
</head>
<body><br>
	<div class="button-box col-lg-12 offset-md-1">
<h1><a	class="btn btn-secondary " href='${backSearchPage}'>Back</a></h1>
<h1>
			List of Users <a class="btn btn-secondary offset-md-1 "
				href='${homePage}'>Home Page</a> <a
				class="btn btn-danger offset-md-1"
				href="/UTA_Mac_FMS/LogoutController">Logout</a>
		</h1>

<h3><small class="offset-md-5"><strong>Click on User Name to view more details!!</strong></small></h3>
		<div>
			<form action="/UTA_Mac_FMS/AdminController" method="GET">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">User Name</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Role</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${USERS}" var="user" varStatus="status">
								<tr>
									<%-- <td style="text-align: center"><input onclick="document.getElementById('submit').disabled = false;"  type="radio" id="radioUser${status.count}" name="radioUser" value="${status.count}"></td> --%>
									<td><a href="/UTA_Mac_FMS/ViewSpecificUserController?action=viewUser&viewSpecificUser=${user.username}"> <c:out value="${user.username}"/> </a></td>
									<td><c:out value="${user.firstname}" /></td>
									<td><c:out value="${user.lastname}" /></td>
									<td><c:out value="${user.role}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- <br> <input type="submit" class="btn btn-primary col-md-3 offset-md-3" id="submit" value="View User Details" disabled> <br> <br> -->
			</form>
		</div>
		</div>
</body>
</html>