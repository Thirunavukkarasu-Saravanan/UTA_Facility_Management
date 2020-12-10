package com.group4.macfms.model;

public class MarErrorMsgs {
	private String descriptionError;
	private String assignedToError;
	private String assignMarError;
	private String assignRuleCheckError;
	
	
	public String getAssignRuleCheckError() {
		return assignRuleCheckError;
	}
	public void setAssignRuleCheckError(String assignRuleCheckError) {
		this.assignRuleCheckError = assignRuleCheckError;
	}
	public String getAssignedToError() {
		return assignedToError;
	}
	public void setAssignedToError(String assignedToError) {
		this.assignedToError = assignedToError;
	}
	public String getAssignMarError() {
		return assignMarError;
	}
	public void setAssignMarError(String assignMarError) {
		this.assignMarError = assignMarError;
	}
	
	public String getDescriptionError() {
		return descriptionError;
	}
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}

}
