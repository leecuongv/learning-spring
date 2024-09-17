package com.cuonglv.learning_spring.utility.model.db;

import lombok.Data;

@Data
public class ProcedureCalling2 {
	public String procName;
	public String params;
	public String dataSourceName;

	public ProcedureCalling2(String procName, String params, String dataSourceName) {
		this.procName = procName;
		this.params = params;
		this.dataSourceName = dataSourceName;
	}
}
