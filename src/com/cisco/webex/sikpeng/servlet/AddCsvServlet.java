package com.cisco.webex.sikpeng.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cisco.webex.sikpeng.util.HandleCsv;

/**
 * Servlet implementation class AddCsvServlet
 */
@WebServlet("/AddCsvServlet")
public class AddCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCsvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String queryId = request.getParameter("queryId");
		String query = request.getParameter("query");
		String bugNumber = request.getParameter("currentBugNumber");
		
		HandleCsv handler = new HandleCsv();
		handler.add(queryId, query, bugNumber);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
