package com.cisco.webex.sikpeng.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.cisco.webex.sikpeng.Bug;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MyHttpURLConnection {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) {
		String query ="Project:CSC.voice and Product:meetingplace minus Severity:4,5,6 minus Status:J,U,D,C,R,V";
		int bugNumber=-3;;
		MyHttpURLConnection myHttpURLConnection = new MyHttpURLConnection();
		try {
			 bugNumber = myHttpURLConnection.getBugNumber(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(bugNumber);
	}
	public int getBugNumber(String currentQuery)  {
		int bugNumber = -1;
		
		try{
			
			String r = new MyHttpURLConnection().send(currentQuery);
			Gson gson = new Gson();
			Bug[] bugs = gson.fromJson(r, Bug[].class);
			System.out.println("The query received " + bugs.length + " bugs.");
			bugNumber = bugs.length;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bugNumber =-2;
		} 
		
		return bugNumber;
	}

	public String send(String query) throws Exception {

		// encode the query
		System.out.println("Query received in MyHttpURLConnection:");
		System.out.println(query);
		String uri = "http://wwwin-metrics.cisco.com/cgi-bin/ddts_query.cgi?expert="
				+ URLEncoder.encode(query, "UTF-8") + "&type=json";
		System.out.println("Query UrlEncoded:");
		System.out.println(uri);

		MyHttpURLConnection http = new MyHttpURLConnection();

		// send encoded query and get response as plain text
		String r = http.sendGet(uri);
		System.out.println(r);

		// convert plain text result to json object

		return r;
	}

	// HTTP GET request
	private String sendGet(String uri)  {
		BufferedReader in = null;
		URL obj;
		StringBuffer response = null;
		try {
			obj = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("Sending 'GET' request to URL : " + uri);
			System.out.println("Response Code : " + responseCode);

			in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return response.toString();

	}
}
