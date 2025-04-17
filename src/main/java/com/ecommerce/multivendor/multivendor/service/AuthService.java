package com.ecommerce.multivendor.multivendor.service;

import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.request.LoginRequest;
import com.ecommerce.multivendor.multivendor.response.AuthResponse;
import com.ecommerce.multivendor.multivendor.response.SignupRequest;


public interface AuthService {

	String createUser(SignupRequest request) throws Exception;
	void sentLoginOtp(String email,UserRole role) throws Exception;
	AuthResponse signing(LoginRequest request);
	
}
