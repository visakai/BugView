package com.cisco.webex.sikpeng.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cisco.webex.sikpeng.model.Query;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class HandleCsv {

	String CSV_File = "C:\\Users\\sikpeng\\workspace\\BugView\\WebContent\\resource\\query.csv";
	String CSV_Splitter = ";";
	BufferedReader reader = null;
	BufferedWriter writer = null;
	
	public static void main(String [] args){
		HandleCsv handleCsv =  new HandleCsv();
		List<Query> queries = handleCsv.getQueryObjs();
		System.out.println(queries.get(0).getQuery());
	}
	// this method will read csv file and parse it into json format and respond back
	public String read() {
		return getJsonArray().toString();
	}

	
	public JsonArray getJsonArray() {
		JsonArray queries = new JsonArray();
		try {
			reader = new BufferedReader(new FileReader(CSV_File));

			String line = "";

			while ((line = reader.readLine()) != null) {
				JsonObject query = new JsonObject();
				String[] s = line.split(CSV_Splitter);
				query.addProperty("id", s[0]);
				query.addProperty("query", s[1]);
				query.addProperty("bug", s[2]);
				queries.add(query);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if (reader != null) { 
				try { 
					reader.close();  
				}
				catch (IOException e) { 
					e.printStackTrace(); 
				} 
			}
		}
				
		return queries;
	}
	
	public List<Query> getQueryObjs() {
		List<Query> queries = new ArrayList<Query>();
		
		try {
			reader = new BufferedReader(new FileReader(CSV_File));

			String line = "";
			int i=0;
			while ((line = reader.readLine()) != null) {
				
				Query query = new Query();
				String[] s = line.split(CSV_Splitter);
				query.setId(s[0]);
				query.setQuery(s[1]);
				query.setBugNumber(s[2]);
				queries.add(query);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			if (reader != null) { 
				try { 
					reader.close();  
				}
				catch (IOException e) { 
					e.printStackTrace(); 
				} 
			}
		}
				
		return queries;
	}
	
	
	public void add(String queryId, String query, String bugNumber)  {
	    FileWriter fileWritter;
		try {
			fileWritter = new FileWriter(CSV_File, true); // true means append, not overwrite
			writer = new BufferedWriter(fileWritter);
			writer.write(queryId + ";" + query + ";" + bugNumber + "\n");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	/*
	 * public void run() {
	 * 
	 * String line = "";
	 * 
	 * 
	 * try {
	 * 
	 * br = new BufferedReader(new FileReader(csvFile)); while ((line =
	 * br.readLine()) != null) {
	 * 
	 * // use comma as separator String[] query = line.split(cvsSplitBy);
	 * 
	 * System.out.println("Query [ID= " + query[0] + " , content=" + query[1] +
	 * "]");
	 * 
	 * MyHttpURLConnection http = new MyHttpURLConnection (); String s = ""; int
	 * bugN=-2; try { bugN= http.getBugNumber(query[1]);
	 * 
	 * System.out.println(bugN); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } finally { if (br != null) { try
	 * { br.close(); } catch (IOException e) { e.printStackTrace(); } } }
	 * 
	 * System.out.println("Done"); }
	 */

	public void writeQueryObjsToCsv (List<Query> queries){
		
		 FileWriter fileWritter;
			try {
				fileWritter = new FileWriter(CSV_File, false); // false means overwrite original content, true means append
				writer = new BufferedWriter(fileWritter);
				for (int i=0; i<queries.size(); i++) {
					Query query = queries.get(i);
					writer.write(query.getId() + ";" + query.getQuery() + ";" + query.getBugNumber() + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	}
}