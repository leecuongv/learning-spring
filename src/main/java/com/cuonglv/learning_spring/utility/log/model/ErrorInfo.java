package com.cuonglv.learning_spring.utility.log.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorInfo {
	@Expose
	@SerializedName("errorCode")
	protected String errorCode;

	@Expose
	@SerializedName("message")
	protected String message;

	@Expose
	@SerializedName("stackTrace")
	protected String stackTrace;

	public ErrorInfo() {
	}

	public ErrorInfo(String errorCode) {
		if (errorCode.equalsIgnoreCase("00")) {
			this.errorCode = errorCode;
			this.message = "Success";
		}
	}

	public ErrorInfo(String errorCode, String message, String stackTrace) {
		this.errorCode = errorCode;
		this.message = message;
		this.stackTrace = stackTrace;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return this.stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}
