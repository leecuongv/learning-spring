package com.cuonglv.learning_spring.context;

import javax.annotation.ManagedBean;

import org.springframework.web.context.annotation.RequestScope;

import com.cuonglv.learning_spring.utility.log.model.SI_Log;

import lombok.Data;

@ManagedBean
@RequestScope
@Data
public class RequestContext {

	public String requestId;
	public Object boby;
	public String service;
	public SI_Log siLog;
}