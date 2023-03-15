package com.infmglproject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class Search
 */

@WebServlet("/adminSearchAccounts")
public class AdminSearchAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that gets the search term of the session in the admin customer accounts page:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpSession is used for retrieving/storing specific information; To be used for storing the input on the search bar after clicking the search button:			
		HttpSession session = request.getSession();
		String searchTerm = null;
		
		// Gets and stores the search input session:
		String searchTermSession = (String)session.getAttribute("searchTerm");
		
		// Gets and stores the search input parameter:
		String searchTermParameter = request.getParameter("searchTerm");

		if (searchTermParameter != null) {
		    // Sets the search parameter as the session attribute if parameter is not null then assigns it to the "searchTerm" string:
		    session.setAttribute("searchTerm", searchTermParameter);
		    searchTerm = searchTermParameter;
		} 
		else if (searchTermSession != null) {
		    searchTerm = searchTermSession;
		}
		
		// Set and forward dispatcher:
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminSearchAccounts.jsp");
		dispatcher.forward(request, response);
   }
}