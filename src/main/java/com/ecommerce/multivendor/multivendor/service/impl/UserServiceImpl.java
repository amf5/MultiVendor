package com.ecommerce.multivendor.multivendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.config.JwtProvider;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.UserRepository;
import com.ecommerce.multivendor.multivendor.service.UserService;
@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;
@Autowired
private JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email=jwtProvider.getEmailFromJwtToken(jwt);
	User user=	this.findUserByEmail(email);
	
	if(user==null) {
		throw new Exception("user not found with email -"+email);
	}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("user not found with email -"+email);
		}
		return user;
	}

}
