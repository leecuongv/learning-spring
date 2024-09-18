package com.cuonglv.learning_spring.utility.model.db;

import lombok.Data;

@Data
public class StatementCalling {

	public String sql;
	public String params;

	public StatementCalling(String sql, String params) {
		super();
		this.sql = sql;
		this.params = params;
	}

}
