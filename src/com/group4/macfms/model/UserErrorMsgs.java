package com.group4.macfms.model;

public class UserErrorMsgs {
	
	private String commonerrorMsg;
	private String usernameError;
	private String passwordError;
	private String confirmPasswordError;
	private String fnameError;
	private String lnameError;
	private String idError;
	private String emailError;
	private String phoneError;
	private String addressError;
	private String cityError;
	private String zipCodeError;
	
	private String loginErrMsg;
	private String userNotExistError;

	public UserErrorMsgs() {
		this.setUsernameError("");
		
		this.setLoginErrMsg("");
		this.setUserNotExistError("");
	}
	
	public String getCommonerrorMsg() {
		return commonerrorMsg;
	}
	public void setCommonerrorMsg() {
		if(!usernameError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !passwordError.equals("") )
			this.commonerrorMsg = "Please correct the following:";
		else if( !confirmPasswordError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if(!fnameError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !lnameError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !idError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !zipCodeError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if(!addressError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !cityError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if( !phoneError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else if	(!emailError.equals(""))
			this.commonerrorMsg = "Please correct the following:";
		else
			this.commonerrorMsg = "";
	}
	
	public String getUsernameError() {
		return usernameError;
	}
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public String getConfirmPasswordError() {
		return confirmPasswordError;
	}
	public void setConfirmPasswordError(String confirmPasswordError) {
		this.confirmPasswordError = confirmPasswordError;
	}
	public String getFnameError() {
		return fnameError;
	}
	public void setFnameError(String fnameError) {
		this.fnameError = fnameError;
	}
	public String getLnameError() {
		return lnameError;
	}
	public void setLnameError(String lnameError) {
		this.lnameError = lnameError;
	}
	public String getIdError() {
		return idError;
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getEmailError() {
		return emailError;
	}
	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}
	public String getPhoneError() {
		return phoneError;
	}
	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}
	public String getAddressError() {
		return addressError;
	}
	public void setAddressError(String addressError) {
		this.addressError = addressError;
	}
	public String getCityError() {
		return cityError;
	}
	public void setCityError(String cityError) {
		this.cityError = cityError;
	}
	public String getZipCodeError() {
		return zipCodeError;
	}
	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}
	public String getLoginErrMsg() {
		return loginErrMsg;
	}
	public void setLoginErrMsg(String loginErrMsg) {
		this.loginErrMsg = loginErrMsg;
	}
	public String getUserNotExistError() {
		return userNotExistError;
	}
	public void setUserNotExistError(String userNotExistError) {
		this.userNotExistError = userNotExistError;
	}
}
