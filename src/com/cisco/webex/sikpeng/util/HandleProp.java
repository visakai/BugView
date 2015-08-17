package com.cisco.webex.sikpeng.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.google.gson.JsonObject;

public class HandleProp {

	String filename = "C:\\Users\\sikpeng\\workspace\\BugView\\WebContent\\resource\\config.properties";

	public static void main(String[] args) {
		HandleProp handleProp = new HandleProp();
		 //handleProp.read();
		handleProp.setLastUpdated("666");
		handleProp.setBatchTimeInMin("888");
	}

	public void setLastUpdated(String lastUpdated) {
		
		JsonObject j = getPropAsJson();
		String updateFrequency = j.get("updateFrequency").getAsString();
		String batchTimeInMin = j.get("batchTimeInMin").getAsString();
		
		Properties prop = new Properties();
				
		OutputStream output = null;
		try {

			output = new FileOutputStream(filename);

			// set the properties value 
			prop.setProperty("updateFrequency", updateFrequency);
			prop.setProperty("lastUpdated", lastUpdated);
			prop.setProperty("batchTimeInMin", batchTimeInMin);
			
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} 
	}
	
public void setBatchTimeInMin(String batchTimeInMin) {
		
		JsonObject j = getPropAsJson();
		String updateFrequency = j.get("updateFrequency").getAsString();
		String lastUpdated = j.get("lastUpdated").getAsString();
		
		
		Properties prop = new Properties();
				
		OutputStream output = null;
		try {

			output = new FileOutputStream(filename);

			// set the properties value 
			prop.setProperty("updateFrequency", updateFrequency);
			prop.setProperty("lastUpdated", lastUpdated);
			prop.setProperty("batchTimeInMin", batchTimeInMin);
			
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} 
	}

	public String read(){
		return getPropAsJson().toString();
    }
	
	public JsonObject getPropAsJson(){
		Properties prop = new Properties();
    	InputStream input = null;
    	JsonObject configProp = new JsonObject();
    	try {
    		input = new FileInputStream(filename);
    		
     		//load a properties file from file system
    		prop.load(input);
   
    	    configProp.addProperty("updateFrequency",prop.getProperty("updateFrequency"));
    	    configProp.addProperty("lastUpdated",prop.getProperty("lastUpdated"));
    	    configProp.addProperty("batchTimeInMin",prop.getProperty("batchTimeInMin"));
    	    
    	    System.out.println(prop.getProperty("updateFrequency"));
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        } finally{
        	if(input!=null){
        		try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        }
    	return configProp;
		
	}
	
	
}


