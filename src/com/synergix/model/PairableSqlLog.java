package com.synergix.model;

import com.synergix.interfaces.Pairable;

/**
 * base class for Sql Types those parameters can be paired completely with column name
 * sql update, sql delete, sql select
 * @author Kevin
 *
 */
public abstract class PairableSqlLog extends SqlLog implements Pairable{

	public PairableSqlLog(String rawContent) {
		super(rawContent);
	}

	@Override
	public void pair() {
		// TODO Auto-generated method stub
	}
	
}
