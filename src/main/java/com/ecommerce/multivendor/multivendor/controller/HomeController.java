package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.response.ApiResponse;

@RestController
public class HomeController {

	@GetMapping()
 public ApiResponse HomeControllerHandler() {
	ApiResponse apiResponse=new ApiResponse();
	apiResponse.setMessage("welcome to ecommerce multi vendor system");
	return  apiResponse;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
