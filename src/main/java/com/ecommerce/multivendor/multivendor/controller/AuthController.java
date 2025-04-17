package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.modal.VerificationCode;
import com.ecommerce.multivendor.multivendor.repository.UserRepository;
import com.ecommerce.multivendor.multivendor.request.LoginOtpRequest;
import com.ecommerce.multivendor.multivendor.request.LoginRequest;
import com.ecommerce.multivendor.multivendor.response.ApiResponse;
import com.ecommerce.multivendor.multivendor.response.AuthResponse;
import com.ecommerce.multivendor.multivendor.response.SignupRequest;
import com.ecommerce.multivendor.multivendor.service.AuthService;



@RestController
@RequestMapping("/auth")
public class AuthController {
    
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private AuthService authService;
	@PostMapping("/signup")
	public ResponseEntity<?> createUserHandler(@RequestBody SignupRequest signupRequest) throws Exception{
		String jwt=authService.createUser(signupRequest);
		AuthResponse response=new AuthResponse();
		response.setJwt(jwt);
		response.setMessage("register success");
		response.setRole(UserRole.ROLE_CUSTOMER);
		return ResponseEntity.ok(response);
		}
	
	
	@PostMapping("/sent/login-singup-otp")
	public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest request) 
			throws Exception{
	authService.sentLoginOtp(request.getEmail(),request.getRole());
		ApiResponse response=new ApiResponse();
		
		response.setMessage("otp sent successfully");
		
		return ResponseEntity.ok(response);
		}
	
	@PostMapping("/signing")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest request) 
			throws Exception{
	 AuthResponse authResponse= authService.signing(request);
		

		return ResponseEntity.ok(authResponse);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
