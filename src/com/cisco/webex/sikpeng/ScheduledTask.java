package com.cisco.webex.sikpeng;

import java.util.List;
import java.util.TimerTask;
import java.util.Date;

import com.cisco.webex.sikpeng.model.Query;
import com.cisco.webex.sikpeng.util.HandleCsv;
import com.cisco.webex.sikpeng.util.HandleProp;
import com.cisco.webex.sikpeng.util.MyHttpURLConnection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	

	public static void main(String[] args) {
		ScheduledTask scheduledTask = new ScheduledTask();
		scheduledTask.run();
	}

	// Add your task here
	public void run() {

		// 1, record current start time
		Date start = new Date(); // initialize date
		System.out.println("Start updating data. Start time is : " + start); // Display current time

		// 2, get JsonArray from CSV handler
		HandleCsv handleCsv = new HandleCsv();
		List<Query> queries = handleCsv.getQueryObjs();

		// 3, read the first line from CSV file, parse the line, get ID and
		// query and send the query out and get result bugNumber back
		for (int i = 0; i < queries.size(); i++) {
			Query query = queries.get(i);
			Date taskStart = new Date(); 
			System.out.println("Start updating query ["+ i +"]");
			System.out.println("queryID: " + query.getId());
			System.out.println("query: " + query.getQuery());
			System.out.println("old bug number: " + query.getBugNumber());
			System.out.println("Task started at : "	+ taskStart); 
			
			 		
			try {
				MyHttpURLConnection myHttpURLConnection = new MyHttpURLConnection();
				
				String newBugNumber = Integer.toString( myHttpURLConnection.getBugNumber(query.getQuery()) );
				//int newBugNumber=100;
				// update the bug number
				query.setBugNumber(newBugNumber);
				
				System.out.println("Updated new bug number : "	+ newBugNumber); 
				Date taskEnd = new Date(); 
				System.out.println("Task ended at : " + taskEnd); 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}

		Date end = new Date(); // initialize date
		System.out.println("Finish updating data. Finish time is : " + end); // Display current time
		int batchTimeInMin = Math.round((end.getTime()-start.getTime())/60000);
		System.out.println("In total, it took " + batchTimeInMin + " min to update all " + queries.size() + " records");
		
		
		System.out.println("Ready to update CSV file ");
		handleCsv.writeQueryObjsToCsv(queries);
			
		// write lastUpdated time, batchTimeMin to properties file
		HandleProp handleProp = new HandleProp();
		handleProp.setLastUpdated( Integer.toString(Math.round(end.getTime()/1000)));
		handleProp.setBatchTimeInMin( Integer.toString(Math.round(batchTimeInMin)));
		
	}
	
	
}