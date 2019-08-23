package com.synergix.constant;

import java.util.Arrays;
import java.util.List;

public class SqlKeyWord {
	//rembember to put a space after every sql keyword
	public static final String SQL_INSERT = "INSERT INTO ";
	public static final String SQL_UPDATE = "UPDATE ";
	public static final String SQL_DELETE = "DELETE FROM ";
	public static final String SQL_WHERE = " WHERE "; // WHERE should appear in the middle of the query, so that there must be 2 spaces before and after WHERE
	public static final String SQL_SET = " SET ";
	
	
	public static List<String> getSqlKeyWords() {
		return Arrays.asList(SQL_INSERT, SQL_UPDATE, SQL_DELETE, SQL_WHERE, SQL_SET);
	}

}
