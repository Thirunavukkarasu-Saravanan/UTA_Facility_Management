package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.LoginDAO;
import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("commonErrorMsg");
		session.removeAttribute("errorMessage");
		session.removeAttribute("incorrectPassword");
		String action = request.getParameter("action");
		String url = "";
		User loginUser = new User();
		User dbUser = new User();
		UserErrorMsgs errorMsg = new UserErrorMsgs();
		LoginDAO login = new LoginDAO();

		if (action.equalsIgnoreCase("login")) {

			loginUser.setUsername(request.getParameter("username"));
			loginUser.setPassword(request.getParameter("password"));

			session.setAttribute("userInfo", loginUser);
			loginUser.validateUser(loginUser, errorMsg);

			if (errorMsg.getLoginErrMsg() != "") {
				url = "/index.jsp";
				session.setAttribute("errorMessage", errorMsg.getLoginErrMsg());
				getServletContext().getRequestDispatcher(url).forward(request, response);
			} else {
				session.removeAttribute("errorMessage");
				loginUser.validateUserName(loginUser, errorMsg);
				if (errorMsg.getLoginErrMsg() != "") {
					url = "/index.jsp";
					session.setAttribute("incorrectPassword", errorMsg.getLoginErrMsg());
					getServletContext().getRequestDispatcher(url).forward(request, response);
				} else {
					loginUser.validateUserPassword(loginUser, errorMsg);
					dbUser = login.userCheck(request.getParameter("username"));
						if (dbUser.getRole().equalsIgnoreCase("student/faculty")) {
								session.setAttribute("user", dbUser);
								response.sendRedirect("/UTA_Mac_FMS/userHome.jsp");
								session.setAttribute("homePage", "userHome.jsp");
							} else if (dbUser.getRole().equalsIgnoreCase("facility manager")) {
								session.setAttribute("user", dbUser);
								response.sendRedirect("/UTA_Mac_FMS/managerHome.jsp");
								session.setAttribute("homePage", "managerHome.jsp");
							} else if (dbUser.getRole().equalsIgnoreCase("repairer")) {
								session.setAttribute("user", dbUser);
								response.sendRedirect("/UTA_Mac_FMS/repairerHome.jsp");
								session.setAttribute("homePage", "repairerHome.jsp");
							} else {
								session.setAttribute("user", dbUser);
								response.sendRedirect("/UTA_Mac_FMS/adminHome.jsp");
								session.setAttribute("homePage", "adminHome.jsp");
							}
					
				}
			}
		}

		if (action.equalsIgnoreCase("register")) {
			User user = new User();
			LoginDAO register = new LoginDAO();
			// User registerUser = new User();
			UserErrorMsgs errorMsgs = new UserErrorMsgs();
			// validate user details here
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
			// getUserParam(request, user);

			user.validateUserDetails(user, errorMsgs);

			if (errorMsgs.getCommonerrorMsg() != "") {
				url = "/register.jsp";
				session.setAttribute("errorMessage", errorMsgs);
				session.setAttribute("commonErrorMsg", "Please correct the following errors");
				getServletContext().getRequestDispatcher(url).forward(request, response);
			} else {

				int status = register.insertUser(user);
				// show registration success message and redirect to login page
				if (status == 1) {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n"
							+ "    <strong>Woohoo! Registration Successful!</strong> Please login to view your profile!!\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='login_link' class=\"btn btn-primary offset-md-1 \" href = 'index.jsp'> Login</a></h2>";
					htmlRespone += "</html>";

					// return response
					out.println(htmlRespone);
				} else {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
							+ "    <strong>We are facing system issues! Please try registering again! Sorry for the inconvinience!</strong>\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='register' class=\"btn btn-primary offset-md-1 \" href='register.jsp'>Back to Registration Page</a></h2>";
					htmlRespone += "</html>";

					out.println(htmlRespone);
				}
			}
		}
	}

}
