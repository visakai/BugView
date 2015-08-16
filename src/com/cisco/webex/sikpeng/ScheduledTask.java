package com.cisco.webex.sikpeng;

import java.util.TimerTask;
import java.util.Date;

// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {
 
	Date start; // to display current time
 
	// Add your task here
	public void run() {
		
		//1, record current start time
		start = new Date(); // initialize date
		System.out.println("Start time is : " + start); // Display current time
		
		//2, read the first line from CSV file
		
		//3 parse the line, get ID and query and send the query out and get result bugNumber back
		
		//4 save the bugNumber and queryID to Json object in memory
		
		// read next line, repeat
		
		// unitl no line to read
		
		// record finished time
		
		// delete original CSV file 
		
		// write updated data to CSV file
		
		//write lastUpdate time to properties file
		
		// clear  map etc
		
		// finished
		
	}
}