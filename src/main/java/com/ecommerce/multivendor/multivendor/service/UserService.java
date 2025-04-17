package com.ecommerce.multivendor.multivendor.service;

import com.ecommerce.multivendor.multivendor.modal.User;

public interface UserService {
	
User findUserByJwtToken(String jwt) throws Exception;
User findUserByEmail(String email) throws Exception;
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
