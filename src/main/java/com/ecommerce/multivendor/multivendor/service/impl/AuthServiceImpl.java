package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.config.JwtProvider;
import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.modal.VerificationCode;
import com.ecommerce.multivendor.multivendor.repository.CartRepository;
import com.ecommerce.multivendor.multivendor.repository.SellerRepository;
import com.ecommerce.multivendor.multivendor.repository.UserRepository;
import com.ecommerce.multivendor.multivendor.repository.VerificationCodeRepository;
import com.ecommerce.multivendor.multivendor.request.LoginRequest;
import com.ecommerce.multivendor.multivendor.response.AuthResponse;
import com.ecommerce.multivendor.multivendor.response.SignupRequest;
import com.ecommerce.multivendor.multivendor.service.AuthService;
import com.ecommerce.multivendor.multivendor.service.EmailService;
import com.ecommerce.multivendor.multivendor.util.OtpUtil;
@Service
public class AuthServiceImpl implements AuthService {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private CartRepository cartRepository;
   @Autowired
   private JwtProvider jwtProvider;
   @Autowired
   private VerificationCodeRepository codeRepository;
   @Autowired
   private EmailService emailService;
   @Autowired
   private CustomUserServiceImpl customUserServiceImpl;
   @Autowired
   private SellerRepository sellerRepository;
	@Override
	public String createUser(SignupRequest request) throws Exception {
		System.out.println("email"+request.getEmail());
	 VerificationCode verificationCode=codeRepository.findByEmail(request.getEmail());

	 if(verificationCode==null||!verificationCode.getOtp().equals(request.getOtp())) {
		 System.out.println("email_CODE_FALSE"+verificationCode.getEmail());
		 throw new Exception("wrong otp.....");
		 }
	 
	 User user=userRepository.findByEmail(request.getEmail());
		Authentication authentication = null;
		if(user==null) {
			User createdUser=new User();
			createdUser.setEmail(request.getEmail());
			createdUser.setFullName(request.getFullName());
			createdUser.setRole(UserRole.ROLE_CUSTOMER);
			createdUser.setMobile("89614244536600");
			createdUser.setPassword(passwordEncoder.encode(request.getOtp()));
			 user=userRepository.save(createdUser);
			Cart cart=new Cart();
			cart.setUser(user);
			
			cartRepository.save(cart);
			List<GrantedAuthority> authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_CUSTOMER.toString()));
			 authentication=new UsernamePasswordAuthenticationToken(request.getEmail(),null,authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		
		
		
		
		return jwtProvider.generateToken(authentication);
	}
	@Override
	public void sentLoginOtp(String email,UserRole role) throws Exception {
		String SIGNING_PERFIX="signin_";
		String SELLER_PREFIX="seller_";
		
		if(email.startsWith(SIGNING_PERFIX)) {
			email=email.substring(SIGNING_PERFIX.length());
			if(role.equals(UserRole.ROLE_SELLER)) {
				Seller seller=sellerRepository.findByEmail(email);
				if(seller==null) {
					throw new Exception("seller not found");
				}
			
			
			
				
			}
			else {
				System.out.println("email"+email);
				User user=userRepository.findByEmail(email);
				if(user==null) {
					
				throw new Exception("user not exist with provided email");
				
			}
			}
			VerificationCode isExistCode=codeRepository.findByEmail(email);
			if(isExistCode!=null) {
				codeRepository.delete(isExistCode);
			}
		}
			
		String otp=OtpUtil.generateOtp();
		VerificationCode code=new VerificationCode();
		code.setOtp(otp);
		code.setEmail(email);
		
		codeRepository.save(code);
		String subject="zosh bazar login/signup otp";
		System.out.println("otp is====="+otp);
		String text="your login/sugnup otp is -"+otp;
		emailService.sendVerificationOtpEmail(email, otp, subject, text);
		
		
		
		
	}
	
	
	
	
	
	
	private Authentication authenticate(String username, String otp) {
	    UserDetails userDetails = null;
	    
	    try {
	        userDetails = customUserServiceImpl.loadUserByUsername(username);
	    } catch (Exception e) {
	        throw new BadCredentialsException("Invalid userName or Password");
	    }

	    if (userDetails == null) {
	        throw new BadCredentialsException("Invalid userName or Password");
	    }

	    VerificationCode verificationCode = codeRepository.findByEmail(username);
	    if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
	        throw new BadCredentialsException("Wrong OTP");
	    }

	    return new UsernamePasswordAuthenticationToken(
	            userDetails, null, userDetails.getAuthorities());
	}

	@Override
	public AuthResponse signing(LoginRequest request) {
		String username=request.getEmail();
		String otp=request.getOtp();
		Authentication authentication=authenticate(username,otp);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=jwtProvider.generateToken(authentication);
		AuthResponse authResponse=new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("login Success");
		Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
		String roleName=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		authResponse.setRole(UserRole.valueOf(roleName));
			return authResponse;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
