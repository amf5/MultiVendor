package com.ecommerce.multivendor.multivendor.request;

public class LoginRequest {
private String email;
private String otp;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getOtp() {
	return otp;
}
public LoginRequest() {
	super();
}
public LoginRequest(String email, String otp) {
	super();
	this.email = email;
	this.otp = otp;
}
public void setOtp(String otp) {
	this.otp = otp;
}
}
