package com.cisco.webex.sikpeng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



import com.google.gson.Gson;

public class MyHttpURLConnection {
	 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public String send(String query) throws Exception {
 
		
		
		
		//encode the query
		
		String uri ="http://wwwin-metrics.cisco.com/cgi-bin/ddts_query.cgi?expert="+ URLEncoder.encode(query, "UTF-8") + "&type=json";
		System.out.println("Query UrlEncoded:");
		System.out.println(uri);
		
		MyHttpURLConnection http = new MyHttpURLConnection ();
		
		//send encoded query and get response as plain text
		String r = http.sendGet(uri);
		System.out.println(r);
 
		//convert plain text result to json object
		
		
		BugAnalysis  bugAnalysis = new  BugAnalysis ();
		//bugAnalysis.sortById()
		
		//ObjectMapper jacksonMapper = new ObjectMapper();
		Gson gson = new Gson();
		//TypeDTO[] myTypes = gson.fromJson(new FileReader("input.json"), TypeDTO[].class);
		//List<Bug> bugs = gson.fromJson(r, ArrayList.class);
		Bug[] bugs = gson.fromJson(r, Bug[].class);
		
		System.out.println("The query received " + bugs.length + " bugs.");
		return r;
	}
	 
		// HTTP GET request
		private String sendGet(String uri) throws Exception {
	 
			
	 
			URL obj = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + uri);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			
			return response.toString();
	 
			
		}
}

