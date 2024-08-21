package com.cuonglv.learning_spring.utility.model.msg.response;

import java.util.Date;

import com.cuonglv.learning_spring.utility.exception.ErrorDetail;

public class Header {

	private String source;
	private String target;
	private String requestId;
	private Date timestamp;
	private int status;
	private String message;
	private ErrorDetail errorDetail;

	public Header() {
	}

	public Header(String source, String target, String requestId, Date timestamp, int status, String message,
			ErrorDetail errorDetail) {
		super();
		this.source = source;
		this.target = target;
		this.requestId = requestId;
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.errorDetail = errorDetail;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorDetail getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}

}
