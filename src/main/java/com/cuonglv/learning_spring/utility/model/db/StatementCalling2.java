package com.cuonglv.learning_spring.utility.model.db;

import lombok.Data;

@Data
public class StatementCalling2 {

	private String sql;
	private String params;
	private String dataSourceName;

	public StatementCalling2(String sql, String params, String dataSourceName) {
		super();
		this.sql = sql;
		this.params = params;
		this.dataSourceName = dataSourceName;
	}

}
