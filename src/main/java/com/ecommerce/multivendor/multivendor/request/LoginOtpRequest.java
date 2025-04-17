package com.ecommerce.multivendor.multivendor.request;

import com.ecommerce.multivendor.multivendor.domain.UserRole;

public class LoginOtpRequest {
private String email;
	
	
public LoginOtpRequest() {
	super();
}

public LoginOtpRequest(String email, String otp, UserRole role) {
	super();
	this.email = email;
	this.otp = otp;
	this.role = role;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public UserRole getRole() {
	return role;
}

public void setRole(UserRole role) {
	this.role = role;
}

private String otp;

private UserRole role;
}
