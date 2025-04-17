package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.config.JwtProvider;
import com.ecommerce.multivendor.multivendor.domain.AccountStatus;
import com.ecommerce.multivendor.multivendor.exception.SellerException;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.SellerReport;
import com.ecommerce.multivendor.multivendor.modal.VerificationCode;
import com.ecommerce.multivendor.multivendor.repository.VerificationCodeRepository;
import com.ecommerce.multivendor.multivendor.request.LoginRequest;

import com.ecommerce.multivendor.multivendor.response.AuthResponse;
import com.ecommerce.multivendor.multivendor.service.AuthService;
import com.ecommerce.multivendor.multivendor.service.EmailService;
import com.ecommerce.multivendor.multivendor.service.SellerReportService;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import com.ecommerce.multivendor.multivendor.util.OtpUtil;

import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/sellers")
public class SellerController {
@Autowired
private SellerService sellerService;
@Autowired
private VerificationCodeRepository verificationCodeRepository;
@Autowired
private AuthService authService;

private OtpUtil otpUtil;
@Autowired
private EmailService emailService;
@Autowired
private JwtProvider jwtProvider;
@Autowired
private SellerReportService sellerReportService;




@PostMapping("/login")
public  ResponseEntity<AuthResponse> LoginSeller(@RequestBody LoginRequest req) throws Exception{
	
	String otp=req.getOtp();
	String email=req.getEmail();
	
	req.setEmail("seller_"+email);
	AuthResponse authResponse=authService.signing(req);
	
return ResponseEntity.ok(authResponse);	
	
}

@PatchMapping("/verify/{otp}")
public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception{
	
	VerificationCode verificationCode=verificationCodeRepository.findByOtp(otp);
if(verificationCode ==null||!verificationCode.getOtp().equals(otp)) {
	
	throw new Exception("wrong otp....");
}
	Seller seller=sellerService.verifyEmail(verificationCode.getEmail(), otp);
	return ResponseEntity.ok(seller);
}

@PostMapping
public ResponseEntity<Seller> createSeller(@RequestBody Seller seller )throws Exception ,MessagingException{
   Seller savedSeller=sellerService.createSeller(seller);

   String otp=OtpUtil.generateOtp();
	VerificationCode code=new VerificationCode();
	code.setOtp(otp);
	code.setEmail(seller.getEmail());
	
	verificationCodeRepository.save(code);
   String subject="Ecommerce Email Verification Code";
   String text="welcome to Ecommerce Applicatin,verify your account using this Link";
   String frontend_url="http://localhost:8081/verify-seller/";
   emailService.sendVerificationOtpEmail(seller.getEmail(), code.getOtp(), subject, text+frontend_url);
   
   
    
    return new  ResponseEntity(savedSeller,HttpStatus.CREATED);
}

@GetMapping("/{id}")
public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException{
    Seller seller=sellerService.getSellerById(id);
	return new ResponseEntity<>(seller,HttpStatus.OK);
}
@GetMapping("/profile")
public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
 
    Seller seller=sellerService.getSellerProfile(jwt);
    return new ResponseEntity<>(seller,HttpStatus.OK);
    
}


@GetMapping("/report")
public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception {
 
    Seller seller=sellerService.getSellerProfile(jwt);
    SellerReport sellerReport=sellerReportService.getSellerReport(seller);
    return new ResponseEntity<>(sellerReport,HttpStatus.OK);
    
}

@GetMapping
public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false) AccountStatus status){
	List<Seller>sellers=sellerService.getAllSellers(status);
	return ResponseEntity.ok(sellers);
	
	
}




@PatchMapping()
public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt,@RequestBody Seller seller) throws Exception{
	
	Seller profile=sellerService.getSellerProfile(jwt);
	Seller updatedSeller=sellerService.updateSeller(profile.getId(), seller);
return ResponseEntity.ok(updatedSeller)	;
}


@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
	
sellerService.deleteSeller(id);
return ResponseEntity.noContent().build();
}







































}
