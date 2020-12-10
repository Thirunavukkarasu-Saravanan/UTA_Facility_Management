<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			$("#submit :input").prop("disabled", true);
		});
	}
</script>
<title>Facilities - UTA Mac FMS</title>
</head>
<body onload='onPageLoad();'><br>
<div class="button-box col-lg-12 offset-md-1">
<h1><a	class="btn btn-secondary " href='${backFacilitySearchPage}'>Back</a> </h1>
	<h1>
		List of Facilities Available <a class="btn btn-secondary offset-md-1 "
			href='${homePage}'>Home Page</a> <a
			class="btn btn-danger offset-md-1"
			href="/UTA_Mac_FMS/LogoutController">Logout</a>
	</h1>
	<h3>
		<small class="offset-md-7"><strong>Click on Facility Name to view more details!!</strong></small>
	</h3>
	<div>
		<form
			action="/UTA_Mac_FMS/FacilityController?action=listSpecificFacility"
			method="POST">
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<!-- <th scope="col" style="text-align: center">Select a Facility</th> -->
							<th scope="col">Facility Type</th>
							<th scope="col">Facility Name</th>
							<th scope="col">Venue</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${FACILITIES}" var="facility" varStatus="status">
							<tr>
								<%-- <td style="text-align: center"><input onclick="document.getElementById('submit').disabled = false;" type="radio" id="radioFacility${status.count}" name="radioFacility" value="${status.count}"></td> --%>
								<td><c:out value="${facility.facility}" /></td>
								<td><a href="/UTA_Mac_FMS/ViewSpecificFacilityController?action=viewFacility&viewSpecificFacility=${facility.facilityName}"> <c:out value="${facility.facilityName}"/> </a></td>
								<%-- <td><c:out value="${facility.facilityName}" /></td> --%>
								<td><c:out value="${facility.type}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			 <input type="submit" class="btn btn-primary col-md-3 offset-md-3" id="submit"
				value="View Facility Details" disabled> <br> <br>
		</form>
	</div>
	</div>
</body>
</html>