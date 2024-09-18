package com.cuonglv.learning_spring.utility.model.msg.response;

public class ResponseMessage<T> {

	public Header header;
	public Body<T> body;

	public ResponseMessage() {
	}

	public ResponseMessage(Header header, Body<T> body) {
		super();
		this.header = header;
		this.body = body;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body<T> getBody() {
		return body;
	}

	public void setBody(Body<T> body) {
		this.body = body;
	}

}
