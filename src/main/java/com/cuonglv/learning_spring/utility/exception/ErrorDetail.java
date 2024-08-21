package com.cuonglv.learning_spring.utility.exception;

import lombok.Data;

@Data
public class ErrorDetail {

	private int errorCode;
	private String errorMessage;
	private Object stackTrace;

	public ErrorDetail() {
	}

	public ErrorDetail(int errorCode, String errorMessage, Object stackTrace) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.stackTrace = stackTrace;
	}
}
