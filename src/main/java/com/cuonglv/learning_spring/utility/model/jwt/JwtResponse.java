package com.cuonglv.learning_spring.utility.model.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	public static final long serialVersionUID = -8091879091924046844L;
	public final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
