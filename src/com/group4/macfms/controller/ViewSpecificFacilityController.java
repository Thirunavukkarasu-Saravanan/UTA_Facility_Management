package com.group4.macfms.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.SearchFacilityDAO;
import com.group4.macfms.model.Facility;

/**
 * Servlet implementation class ViewSpecificFacilityController
 */
@WebServlet("/ViewSpecificFacilityController")
public class ViewSpecificFacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchFacilityDAO searchDb = new SearchFacilityDAO();
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		Facility searchFacility = new Facility();
		Facility dbFacility = new Facility();		

		searchFacility.setFacilityName(request.getParameter("viewSpecificFacility"));
		try {
			dbFacility = searchDb.searchSpecificFacilityDetails(request.getParameter("viewSpecificFacility"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("facility", dbFacility);
		session.setAttribute("backListPage", "searchFacility.jsp");
		getServletContext().getRequestDispatcher("/viewFacility.jsp").forward(request, response);	
		}
	}
