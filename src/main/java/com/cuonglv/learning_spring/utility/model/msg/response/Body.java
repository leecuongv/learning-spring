package com.cuonglv.learning_spring.utility.model.msg.response;

public class Body<T> {
	T data;

	public Body() {
	}

	public Body(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
