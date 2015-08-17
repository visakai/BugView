package com.cisco.webex.sikpeng.servlet;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cisco.webex.sikpeng.ScheduledTask;
import com.cisco.webex.sikpeng.util.HandleProp;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {

		Timer timer = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); 		// Instantiate SheduledTask class
		
		HandleProp handleProp = new HandleProp();
		JsonObject j = handleProp.getPropAsJson();
		int updateFrequency = j.get("updateFrequency").getAsInt();
		
		timer.schedule(st, 1000*60*100, 1000*60*updateFrequency); //(task, delay in milisecond, interval period in milisecond) Create Repetitively (1 secs = 1000)

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
