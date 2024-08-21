package com.cuonglv.learning_spring.utility.log.model;

public class ProcessInfo {

	InstanceInfo instanceInfo;
	String caseID;
	String caseID_Ref;
	String processData;

	public InstanceInfo getInstanceInfo() {
		return instanceInfo;
	}

	public void setInstanceInfo(InstanceInfo instanceInfo) {
		this.instanceInfo = instanceInfo;
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getCaseID_Ref() {
		return caseID_Ref;
	}

	public void setCaseID_Ref(String caseID_Ref) {
		this.caseID_Ref = caseID_Ref;
	}

	public String getProcessData() {
		return processData;
	}

	public void setProcessData(String processData) {
		this.processData = processData;
	}

}
