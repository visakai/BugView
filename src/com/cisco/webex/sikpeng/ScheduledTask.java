package com.cisco.webex.sikpeng;

import java.util.TimerTask;
import java.util.Date;

// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {
 
	Date start; // to display current time
 
	// Add your task here
	public void run() {
		start = new Date(); // initialize date
		System.out.println("Time is : " + start); // Display current time
		
		//1, record current start time
		
		//2, read the first line from properties file
		
		//3 parse the line, get query content and send the query out and get result bugNumber back
		
		//4 save the bugNumber and queryID, querycontent to  map in memory
		
		// read next line, repeat
		
		// unitl no line to read
		
		// record finished time
		
		// delete original properties file 
		
		// write updated data to properties file
		
		// clear  map etc
		
		//automatically refresh the page, let html page pick up properties values, and display timestamp   This is the same if the page loads or user refresh page.
		
	}
}