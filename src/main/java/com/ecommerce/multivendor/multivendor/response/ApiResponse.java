package com.ecommerce.multivendor.multivendor.response;

public class ApiResponse {
private String message;

public ApiResponse() {
	super();
}

public ApiResponse(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
}
