package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.ProfileDAO;
import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class ViewProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();
	
/*	private void getUserParam(HttpServletRequest request, User user) {
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("confirm"), request.getParameter("fname"),
				request.getParameter("lname"), request.getParameter("id"), request.getParameter("phone"),
				request.getParameter("email"), request.getParameter("address"), request.getParameter("city"),
				request.getParameter("state"), request.getParameter("zip"), request.getParameter("role"));
	}*/
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		User user = new User();
		//String action = request.getParameter("action");
		ProfileDAO retriveUser = new ProfileDAO();
		User temp = (User) session.getAttribute("userInfo");
		user = retriveUser.retrieveUserDetails(temp.getUsername());
		
		session.setAttribute("profile", user);
		response.sendRedirect("profilePage.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		String action = request.getParameter("action");
		ProfileDAO updateUser = new ProfileDAO();
		//getUserParam(request, user);
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setConfirmPassword(request.getParameter("confirm"));
		user.setFirstname(request.getParameter("fname"));
		user.setLastname(request.getParameter("lname"));
		user.setId(request.getParameter("id"));
		user.setPhone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		user.setCity(request.getParameter("city"));
		user.setState(request.getParameter("state"));
		user.setZipcode(request.getParameter("zip"));
		user.setRole(request.getParameter("role"));
		
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		
		if(action.equalsIgnoreCase("updateUserDetails"))
		{
			//validate user here
			user.validateUserDetailsAdmin(user, errorMsgs);
			if (errorMsgs.getCommonerrorMsg() != "") {
				session.setAttribute("errorMessage", errorMsgs);
				//session.setAttribute("commonErrorMsg", errorMsg.getCommonerrorMsg());
				getServletContext().getRequestDispatcher("/profilePage.jsp").forward(request, response);
			}
			else
			{				
			int status = updateUser.updateUserDetails(user);
			if(status == 1)
			{
			session.setAttribute("profile", user);
			response.sendRedirect("profilePage.jsp");
			}
			else
			{
				PrintWriter out = response.getWriter();
				String htmlRespone = "<html>";
				htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
						+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
						+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
						+ "    <strong>We are facing some system issues! Please try your profile again! Sorry for the inconvinience!</strong>\r\n"
						+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
						+ "	</div>";
				htmlRespone += "<h2><a id='userprofile' class=\"btn btn-primary offset-md-1 \" href='profilePage.jsp'>Back to Profile Page</a></h2>";
				htmlRespone += "</html>";

				out.println(htmlRespone);			
			}
			}
		}
		
		if(action.equalsIgnoreCase("updateUserDetailsAdmin"))
		{
			//validate user here
			user.validateUserDetailsAdmin(user, errorMsgs);
			if (errorMsgs.getCommonerrorMsg() != "") {
				session.setAttribute("errorMsg", errorMsgs);
				//session.setAttribute("commonErrorMsg", errorMsg.getCommonerrorMsg());
				getServletContext().getRequestDispatcher("/viewUser.jsp").forward(request, response);
			}
			else
			{
				
			int status = updateUser.updateUserDetails(user);
			if(status == 1)
			{
			session.setAttribute("profile", user);
			response.sendRedirect("viewUser.jsp");
			}
			else
			{
				PrintWriter out = response.getWriter();
				String htmlRespone = "<html>";
				htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
						+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
						+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
						+ "    <strong>We are facing some system issues! Please try your profile again! Sorry for the inconvinience!</strong>\r\n"
						+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
						+ "	</div>";
				htmlRespone += "<h2><a id='userprofile' class=\"btn btn-primary offset-md-1 \" href='profilePage.jsp'>Back to Profile Page</a></h2>";
				htmlRespone += "</html>";

				out.println(htmlRespone);			
			}
			}
		}
	}
}
