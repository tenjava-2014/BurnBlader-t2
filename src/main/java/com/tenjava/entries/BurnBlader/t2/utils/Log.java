package com.tenjava.entries.BurnBlader.t2.utils;

public class Log {
	
	public static void info(String tag, String info) {
		System.out.println("INFO: " + tag + " - " + info);
	}
	
	public static void error(String tag, String info) {
		System.out.println("ERROR: " + tag + " - " + info);
	}
	
	public static void error(Exception e) {
		Log.error(e.getLocalizedMessage(), e + "");
	}
	
}
