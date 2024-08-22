package com.cuonglv.learning_spring.context;

import javax.annotation.ManagedBean;

// import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.context.annotation.RequestScope;

import com.cuonglv.learning_spring.utility.log.model.SI_Log;

import lombok.Data;

@ManagedBean
@RequestScope
@Data
public class RequestContext {

	private String requestId;
	private Object boby;
	private String service;
	private SI_Log siLog;
}