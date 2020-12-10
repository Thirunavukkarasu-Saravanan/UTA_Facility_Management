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

/**
 * Servlet implementation class ViewSpecificUserController
 */
@WebServlet("/ViewSpecificUserController")
public class ViewSpecificUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchUserDAO searchDb = new SearchUserDAO();
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		User searchUser = new User();
		User dbuser = new User();		

		searchUser.setUsername(request.getParameter("viewSpecificUser"));
		try {
			dbuser = searchDb.searchSpecificUserDetails(request.getParameter("viewSpecificUser"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("profile", dbuser);
		session.setAttribute("backListPage", "listUsers.jsp");
		getServletContext().getRequestDispatcher("/viewUser.jsp").forward(request, response);	
		}
}
