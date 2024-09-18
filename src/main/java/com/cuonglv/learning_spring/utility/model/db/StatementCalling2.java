package com.cuonglv.learning_spring.utility.model.db;

import lombok.Data;

@Data
public class StatementCalling2 {

	public String sql;
	public String params;
	public String dataSourceName;

	public StatementCalling2(String sql, String params, String dataSourceName) {
		super();
		this.sql = sql;
		this.params = params;
		this.dataSourceName = dataSourceName;
	}

}
