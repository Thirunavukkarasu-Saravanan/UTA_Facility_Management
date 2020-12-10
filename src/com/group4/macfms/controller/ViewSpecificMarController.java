package com.group4.macfms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.SearchMarDAO;
import com.group4.macfms.model.Mar;

/**
 * Servlet implementation class ViewSpecificMarController
 */
@WebServlet("/ViewSpecificMarController")
public class ViewSpecificMarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SearchMarDAO searchDb = new SearchMarDAO();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.removeAttribute("errorMessage");
		Mar searchMar = new Mar();
		Mar searchReport = new Mar();
		Mar dbMar = new Mar();

		if (action.equalsIgnoreCase("viewMar")) {
			searchMar.setMarNumber(request.getParameter("viewSpecificMar"));
			try {
				dbMar = searchDb.searchSpecificMarDetails(request.getParameter("viewSpecificMar"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("mar", dbMar);
			session.setAttribute("backListPage", "listUnassignedMars.jsp");
			getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("viewReport")) {
			searchReport.setMarNumber(request.getParameter("viewSpecificReport"));

				try {
					dbMar = searchDb.searchSpecificMarDetails(request.getParameter("viewSpecificReport"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("mar", dbMar);
				session.setAttribute("backListPage", "listReportedMars.jsp");
				getServletContext().getRequestDispatcher("/viewReportedProblem.jsp").forward(request, response);
		}
	}
}
