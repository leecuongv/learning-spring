package com.cuonglv.learning_spring.utility.model.db;

import lombok.Data;

@Data
public class ProcedureCalling {
	public String procName;
	public String params;

	public ProcedureCalling(String procName, String params) {
		super();
		this.procName = procName;
		this.params = params;
	}
}
