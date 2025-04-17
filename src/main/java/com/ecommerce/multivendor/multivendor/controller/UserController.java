package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.response.AuthResponse;
import com.ecommerce.multivendor.multivendor.response.SignupRequest;
import com.ecommerce.multivendor.multivendor.service.UserService;

@RestController
public class UserController {
@Autowired
private UserService userService;




@GetMapping("/users/profile")
public ResponseEntity<User> createUserHandler(
	@RequestHeader("Authorization")	String jwt) throws Exception{
	User user=userService.findUserByJwtToken(jwt);
	return ResponseEntity.ok(user);
	}
}
