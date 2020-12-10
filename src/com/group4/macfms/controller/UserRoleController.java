package com.group4.macfms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.SearchUserDAO;
import com.group4.macfms.model.User;
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class UserRoleController
 */
@WebServlet("/UserRoleController")
public class UserRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchUserDAO searchDB = new SearchUserDAO();
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		session.removeAttribute("usernameNotExist");
		User userNameErr = new User();
		UserErrorMsgs errorMsg = new UserErrorMsgs();

		User dbuser = new User();	

		String username = request.getParameter("username");
		
		//validate user name
		userNameErr.validateUserExists(username, errorMsg);
		
		if (errorMsg.getUserNotExistError() != "") {
			session.setAttribute("usernameNotExist", errorMsg.getUserNotExistError());
			getServletContext().getRequestDispatcher("/searchUserRole.jsp").forward(request, response);
		} 
		else
		{
			try {
				dbuser = searchDB.searchSpecificUserDetails(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		session.setAttribute("profile", dbuser);
		session.setAttribute("backSearchUserPage", "searchUserRole.jsp");
		getServletContext().getRequestDispatcher("/changeRole.jsp").forward(request, response);	
		}
	}
}
