package com.cuonglv.learning_spring.utility.exception;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Giá trị input không hợp lệ"), AUTHENTICATION_FAILED("Authentication failed"),
	NO_RECORD_FOUND("Record with provided id is not found");

	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
