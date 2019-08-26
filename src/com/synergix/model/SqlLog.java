package com.synergix.model;

public class SqlLog extends Log {
	protected String dateTime;
	protected String sqlQuery;

	public SqlLog(String rawContent) {
		super(rawContent);
		
	}

}
