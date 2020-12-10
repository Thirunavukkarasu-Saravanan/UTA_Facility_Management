package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.MarDAO;
import com.group4.macfms.model.Mar;
import com.group4.macfms.model.MarErrorMsgs;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/MarController")
public class MarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void getUserParam(HttpServletRequest request, Mar mar) {
		mar.setMar(request.getParameter("marnumber"), request.getParameter("type"), request.getParameter("rid"),
				request.getParameter("reportedby"), request.getParameter("urgency"),
				request.getParameter("description"), request.getParameter("datecreated"),
				request.getParameter("assignedto"), request.getParameter("assigneddate"), request.getParameter("time"),
				request.getParameter("status"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("commonErrorMsg");
		session.removeAttribute("errorMessage");
		User user = (User) session.getAttribute("userInfo");
		String username = user.getUsername();

		if (action.equalsIgnoreCase("viewMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listMars();
			session.setAttribute("MARS", marInDB);
			// session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listMars.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewRequestedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listRequestedMars();
			session.setAttribute("MARS", marInDB);
			// session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listRequestedMars.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewUnassignedMarsToRepairer")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listUnassignedMars();
			session.setAttribute("MARS", marInDB);
			// session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listUnassignedMarsToRepairer.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewUnassignedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listUnassignedMars();
			session.setAttribute("MARS", marInDB);
			// session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listUnassignedMars.jsp").forward(request, response);
		}

		if (action.equalsIgnoreCase("viewAssignedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listAssignedMars(username);
			session.setAttribute("MARS", marInDB);
			getServletContext().getRequestDispatcher("/listAssignedRepairs.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewReportedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listReportedMars(username);
			session.setAttribute("MARS", marInDB);
			getServletContext().getRequestDispatcher("/listReportedMars.jsp").forward(request, response);
		}
		
		else // redirect all other gets to post
			doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("commonErrorMsg");
		session.removeAttribute("errorMessage");
		User user = (User) session.getAttribute("userInfo");
		String username = user.getUsername();


		
		if (action.equalsIgnoreCase("listSpecificRequestedMar")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
			int selectedMarIndex;

			if (request.getParameter("radioMar") != null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
			
				marInDB = MarDAO.listRequestedMars();
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(),
						marInDB.get(selectedMarIndex).getFacilityType(),
						marInDB.get(selectedMarIndex).getReservationId(), marInDB.get(selectedMarIndex).getReportedBy(),
						marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(),
						marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(),
						marInDB.get(selectedMarIndex).getAssignedDate(),
						marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);
				session.setAttribute("backListPage", "listRequestedMars.jsp");
				// getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request,
				// response);
				response.sendRedirect("viewRequestedMarToManager.jsp");
			}
		}
		
		if (action.equalsIgnoreCase("viewAssignedMarsByDate")) {

			 String date = request.getParameter("datepicker");

			if (date == null)
			 getServletContext().getRequestDispatcher("/searchRepairs.jsp").forward(request, response);
			 ArrayList<Mar> marInDB = new ArrayList<Mar>();
			 marInDB = MarDAO.viewAssignedMarsByDate(date);
			 session.setAttribute("MARS", marInDB);
			 getServletContext().getRequestDispatcher("/listMars.jsp").forward(request, response);

			}
		
		if (action.equalsIgnoreCase("updateMarDetails")) { 
			session.removeAttribute("assignMarError");
			session.removeAttribute("assignedToError");
			MarDAO marUpdate = new MarDAO();
			MarErrorMsgs errorMsgs = new MarErrorMsgs();
			Mar mar = new Mar();
			getUserParam(request, mar);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String todayDate = dateFormat.format(date);
			mar.setAssignedDate(todayDate);
			
			mar.validateAssignedTo(mar, errorMsgs);			
			if (errorMsgs.getAssignedToError() != "") {
				session.setAttribute("assignedToError", errorMsgs.getAssignedToError());
				getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);
			} 
			else {
										
				// Gets the Assigned Mar Counts for the Repairer
				int [] repMarCounts = marUpdate.getRepairerMarCount(mar);
				
				mar.setMarCountsPerDay(repMarCounts[0]);	// TotalMarPerDay
				mar.setMarCountPerWeek(repMarCounts[1]);	// TotalMarPerWeek
				
				mar.validateAssignRuleCheck(mar, errorMsgs);
							
				
				// Finally Assign the Mar after the Rule checks have been performed
				if (errorMsgs.getAssignRuleCheckError().equals("Mar has been assigned to the repairer "+mar.getAssignedTo()+"")) {
				
					int status = marUpdate.finallyAssignMarToRepairer(mar);
					
					mar.setMarStatusCode(status);
					mar.validateAssignedToStatus(mar, errorMsgs, mar.getMarStatusCode());
					
					session.setAttribute("mar", mar);
					session.setAttribute("assignMarError", errorMsgs.getAssignRuleCheckError());
					response.sendRedirect("viewMar.jsp");
				} else {
					session.setAttribute("mar", mar);
					session.setAttribute("assignMarError", errorMsgs.getAssignRuleCheckError());
					response.sendRedirect("viewMar.jsp");
				}
				
			}
			
		}
		
		
		
		
		// This function allows a repairer to request for a repair
		if (action.equalsIgnoreCase("requestMar")) {
			MarDAO marUpdate = new MarDAO();
			Mar mar = new Mar();
			getUserParam(request, mar);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String todayDate = dateFormat.format(date);
			mar.setAssignedDate(todayDate);
//			if (mar.getAssignedTo() != null || !mar.getAssignedTo().isEmpty() || !mar.getAssignedTo().contains("")) {
			mar.setMarStatus("Requested");
			
//			}
			int status = marUpdate.requestMar(mar,username);
			if (status == 1) {
				session.setAttribute("mar", mar);
				response.sendRedirect("viewMarToRepairer.jsp");
			}
		}
		
		// allows to update EstimatedTime for repairer
		if (action.equalsIgnoreCase("updateMarByRepairer")) {
			MarDAO marUpdate = new MarDAO();
			Mar mar = new Mar();
			getUserParam(request, mar);
			
			if (request.getParameter("request").equalsIgnoreCase("Deny")) {
				mar.setMarStatus("UnAssigned");
				mar.setAssignedTo("null");
			} else {
				mar.setAssignedTo(username);
			}
			
			int status = marUpdate.updateMarByRepairer(mar);
			if (status == 1) {
				session.setAttribute("mar", mar);
//				response.sendRedirect("viewMarToRepairer.jsp");
				response.sendRedirect("viewRepair.jsp");
			}
		}
		
		
		// this function shows a particular unassigned repair to Repairer. This comes before request repair.
		if (action.equalsIgnoreCase("listSpecificUnassignedMarToRepairer")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
			int selectedMarIndex;

			if (request.getParameter("radioMar") != null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listUnassignedMars();
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(),
						marInDB.get(selectedMarIndex).getFacilityType(),
						marInDB.get(selectedMarIndex).getReservationId(), marInDB.get(selectedMarIndex).getReportedBy(),
						marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(),
						marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(),
						marInDB.get(selectedMarIndex).getAssignedDate(),
						marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);
				session.setAttribute("backListPage", "listUnassignedMarsToRepairer.jsp");
				// getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request,
				// response);
				response.sendRedirect("viewMarToRepairer.jsp");
			}
		}
		
		

		if (action.equalsIgnoreCase("listSpecificRepair")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
			int selectedMarIndex;

			if (request.getParameter("radioMar") != null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listAssignedMars(username);
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(),
						marInDB.get(selectedMarIndex).getFacilityType(),
						marInDB.get(selectedMarIndex).getReservationId(), marInDB.get(selectedMarIndex).getReportedBy(),
						marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(),
						marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(),
						marInDB.get(selectedMarIndex).getAssignedDate(),
						marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);
				// getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request,
				// response);
				session.setAttribute("backListPage", "listAssignedRepairs.jsp");
				response.sendRedirect("viewRepair.jsp");
			}
		}

		if (action.equalsIgnoreCase("reportProblem")) {
			MarDAO marInsert = new MarDAO();
			Mar mar = new Mar();
			MarErrorMsgs errorMsgs = new MarErrorMsgs();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String todayDate = dateFormat.format(date);
			mar.setFacilityType(request.getParameter("fname"));
			mar.setDescription(request.getParameter("description"));
			mar.setDateCreated(todayDate);
			mar.setUrgency("Minor");
			mar.setMarStatus("Unassigned");
			User userInfo = (User) session.getAttribute("userInfo");
			String userName = userInfo.getUsername();
			
			mar.validateDescription(mar, errorMsgs);
		
			if (errorMsgs.getDescriptionError() != "") {
				session.setAttribute("descriptionError", errorMsgs.getDescriptionError());
				//session.setAttribute("commonErrorMsg", "Please correct the following errors");
				getServletContext().getRequestDispatcher("/reportMar.jsp").forward(request, response);
			} 
			
			
			else {
				int status = marInsert.insertMar(mar, userName);
				if (status == 1) {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n"
							+ "    <strong>Report submitted successfully! The issue will be addressed by a technician ASAP!!</strong>\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='userHome.jsp'>Back to Home Page</a></h2>";
					htmlRespone += "</html>";

					out.println(htmlRespone);
				} else {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
							+ "    <strong>We are facing some system issues! Please try reporting the problem again! Sorry for the inconvinience!</strong>\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='userHome.jsp'>Back to Home Page</a></h2>";
					htmlRespone += "</html>";

					out.println(htmlRespone);
				}
			}
		}
	}
}
