package com.abc.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	ConfigReader(){ //YOu don't want anyone to access your file
		
	}
	private static ResourceBundle rb = ResourceBundle.getBundle("config"); 
	public static String getValue(String key) {
		return rb.getString(key); //this will give a value
	}
}
 