package com.cuonglv.learning_spring.utility.log.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class SI_Log {

	@Expose
	public String serviceName;
	@Expose
	public ProcessInfo processInfo;
	@Expose
	public String caseId;
	@Expose
	public String caseIdRef;
	@Expose
	public String bpmInput;
	@Expose
	public String bpmOutput;
	@Expose
	public String siInput;
	@Expose
	public String siOutput;
	@Expose
	public String errorCode;
	@Expose
	public String errorMessage;
	@Expose
	public String stacktrace;
	@Expose
	public String logCode;
	@Expose
	public String parentCode;
	@Expose
	public String logId;
	@Expose
	public String startTime;
	@Expose
	public String finishTime;
	@Expose
	public String caller;
	@Expose
	public String targetSystem;

	public SI_Log() {
		this.logCode = UUID.randomUUID().toString();
		this.startTime = convertDateToString(new Date(), "yyyy/MM/dd HH:mm:ss.SSS");
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorCode = errorInfo.getErrorCode();
		this.errorMessage = errorInfo.getMessage();
		this.stacktrace = errorInfo.getStackTrace();
	}

	public ProcessInfo getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(String processInfo) {
		this.processInfo = new Gson().fromJson(processInfo, ProcessInfo.class);
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseIdRef() {
		return caseIdRef;
	}

	public void setCaseIdRef(String caseIdRef) {
		this.caseIdRef = caseIdRef;
	}

	public String getBpmInput() {
		return bpmInput;
	}

	public void setBpmInput(String bpmInput) {
		this.bpmInput = bpmInput;
	}

	public String getBpmOutput() {
		return bpmOutput;
	}

	public void setBpmOutput(String bpmOutput) {
		this.bpmOutput = bpmOutput;
	}

	public String getSiInput() {
		return siInput;
	}

	public void setSiInput(String siInput) {
		this.siInput = siInput;
	}

	public String getSiOutput() {
		return siOutput;
	}

	public void setSiOutput(String siOutput) {
		this.siOutput = siOutput;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public static String convertDateToString(Date date, String format) {

		String result = null;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		result = simpleDateFormat.format(date);

		return result;
	}
}
