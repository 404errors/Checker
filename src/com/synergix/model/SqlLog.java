package com.synergix.model;

public class SqlLog extends Log {
	String dateTime;
	String sqlQuery;

	public SqlLog(String rawContent) {
		super(rawContent);
		
	}

}
