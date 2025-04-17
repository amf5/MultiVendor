package com.ecommerce.multivendor.multivendor.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
@ExceptionHandler(SellerException.class)
public ResponseEntity<ErrorDetails> sellerExceptionHandler(SellerException se, WebRequest re){
	ErrorDetails errorDetails=new ErrorDetails();
	errorDetails.setError(se.getMessage());
	errorDetails.setDetails(re.getDescription(false));
	errorDetails.setTimestamp(LocalDateTime.now());
	return new ResponseEntity<>(errorDetails,HttpStatus.UNPROCESSABLE_ENTITY);
	
	
}
@ExceptionHandler(ProductException.class)
public ResponseEntity<ErrorDetails> productExceptionHandler(SellerException se, WebRequest re){
	ErrorDetails errorDetails=new ErrorDetails();
	errorDetails.setError(se.getMessage());
	errorDetails.setDetails(re.getDescription(false));
	errorDetails.setTimestamp(LocalDateTime.now());
	return new ResponseEntity<>(errorDetails,HttpStatus.UNPROCESSABLE_ENTITY);
	
	
}
}
