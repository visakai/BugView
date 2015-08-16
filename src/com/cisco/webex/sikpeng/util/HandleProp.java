package com.cisco.webex.sikpeng.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.gson.JsonObject;

public class HandleProp {
	
	public static void main(String[] args) {
		HandleProp handleProp = new HandleProp();
		handleProp.read();
	}
	
	public String read(){

    	Properties prop = new Properties();
    	InputStream input = null;
    	JsonObject configProp = new JsonObject();
    	try {
        
    		String filename = "config.properties";
    		input = HandleProp.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		    return null;
    		}

    		//load a properties file from class path, inside static method
    		prop.load(input);
   
    	    configProp.addProperty("updateFrequency",prop.getProperty("updateFrequency"));
    	    configProp.addProperty("lastUpdated",prop.getProperty("lastUpdated"));
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
    	return configProp.toString();
 
    }
}
