package com.synergix.model;

/**
 * base class for Sql Types those parameters can be paired completely with column name
 * sql update, sql delete, sql select
 * @author Kevin
 *
 */
public abstract class PairableSqlLog extends SqlLog{

	public PairableSqlLog(String rawContent) {
		super(rawContent);
	}
	public void pair() {
		
	}
}
