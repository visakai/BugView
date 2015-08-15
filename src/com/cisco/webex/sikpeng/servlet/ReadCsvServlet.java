package com.cisco.webex.sikpeng.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cisco.webex.sikpeng.util.HandleCsv;
import com.google.gson.JsonArray;

/**
 * Servlet implementation class ReadCsvServlet
 */
@WebServlet("/ReadCsvServlet")
public class ReadCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadCsvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HandleCsv handler = new HandleCsv();
		String queries = handler.read();
		System.out.println("in ReadCsvServlet: " + queries);
		response.setContentType("application/json");
		response.getWriter().write(queries);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
