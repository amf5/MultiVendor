package com.ecommerce.multivendor.multivendor.response;

import com.ecommerce.multivendor.multivendor.domain.UserRole;

public class AuthResponse {
private String jwt;
private String message;
private UserRole role;
public AuthResponse() {
	super();
}
public AuthResponse(String jwt, String message, UserRole role) {
	super();
	this.jwt = jwt;
	this.message = message;
	this.role = role;
}
public String getJwt() {
	return jwt;
}
public void setJwt(String jwt) {
	this.jwt = jwt;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public UserRole getRole() {
	return role;
}
public void setRole(UserRole role) {
	this.role = role;
}
}
