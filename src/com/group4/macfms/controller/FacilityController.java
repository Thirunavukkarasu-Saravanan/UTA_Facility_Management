package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.FacilityDAO;
import com.group4.macfms.model.Facility;
import com.group4.macfms.model.FacilityErrorMsgs;
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class FacilityController
 */
@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getUserParam(HttpServletRequest request, Facility facility) {
		facility.setFacility(request.getParameter("facility"), request.getParameter("fname"), request.getParameter("interval"),
				request.getParameter("duration"), request.getParameter("type"), request.getParameter("availability"));
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		session.removeAttribute("facilityNameError");
		
		
		
		if(action.equalsIgnoreCase("listFacilitiesbyType"))
		{
			ArrayList<Facility> facilityTypeInDB = new ArrayList<Facility>();
			facilityTypeInDB = FacilityDAO.listFacilitiesWithType(request.getParameter("ftype"));
			session.setAttribute("FACILITIES", facilityTypeInDB);
			session.setAttribute("backFacilityListPage", "listFacilities.jsp");
			session.setAttribute("backFacilitySearchPage", "searchFacility.jsp");
			response.sendRedirect("listFacilities.jsp");
		}

		else if (action.equalsIgnoreCase("updateFacility")) {
		FacilityDAO facilityUpdate = new FacilityDAO();
		Facility facility = new Facility();
		getUserParam(request, facility);
		int status = facilityUpdate.updateFacilityDetails(facility);
		if(status == 1)
		{
		session.setAttribute("facility", facility);
		session.setAttribute("backFacilityListPage", "listFacilities.jsp");
		response.sendRedirect("viewFacility.jsp");
		}
		}
		else if (action.equalsIgnoreCase("addFacility")) {
			FacilityDAO facilityadd = new FacilityDAO();
			Facility facility = new Facility();
			getUserParam(request, facility);
			FacilityErrorMsgs errorMsg = new FacilityErrorMsgs();
			
			facility.validatefacilityName(facility, errorMsg);
			
			if(errorMsg.getFacilityNameError()!="")
			{
			session.setAttribute("facilityNameError", errorMsg.getFacilityNameError() );
			getServletContext().getRequestDispatcher("/addFacility.jsp").forward(request, response);
			}
		
		else {		
			int status = facilityadd.addFacilityDetails(facility);
			if(status == 1)
			{
				PrintWriter out = response.getWriter();
				String htmlRespone = "<html>";
				htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
						+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
						+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n"
						+ "    <strong>Facility added successfully!</strong>\r\n"
						+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
						+ "	</div>";
				htmlRespone += "<h2><a id='managerhome' class=\"btn btn-primary offset-md-1 \" href='managerHome.jsp'>Back to Home Page</a></h2>";
				htmlRespone += "</html>";

				out.println(htmlRespone);
			}
			else {
				FacilityErrorMsgs errorMessage = new FacilityErrorMsgs();
				errorMessage.setFacilityNameError("Facility name already exists! Try a different name");
				session.setAttribute("facilityNameError", errorMessage.getFacilityNameError());
				getServletContext().getRequestDispatcher("/addFacility.jsp").forward(request, response);
				

			}
				
			
		}
	}			
	}
}
