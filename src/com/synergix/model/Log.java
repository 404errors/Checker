package com.synergix.model;

public class Log {
	private String rawContent;
	
	
	public Log(String rawContent) {
		this.rawContent = rawContent;
	}
	
	public Log() {
		
	}
	
	public String format() {
		return rawContent;
	}

}
