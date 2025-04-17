package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.config.JwtProvider;
import com.ecommerce.multivendor.multivendor.domain.AccountStatus;
import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.exception.SellerException;
import com.ecommerce.multivendor.multivendor.modal.Address;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.repository.AddressRepository;
import com.ecommerce.multivendor.multivendor.repository.SellerRepository;
import com.ecommerce.multivendor.multivendor.service.SellerService;

@Service
public class SellerServiceImpl  implements SellerService{
@Autowired
private SellerRepository sellerRepository;
@Autowired
private JwtProvider jwtProvider;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private AddressRepository addressRepository;

@Override
public Seller getSellerProfile(String jwt) throws Exception {
String email=jwtProvider.getEmailFromJwtToken(jwt);

	return this.getSellerByEmail(email);
}

@Override
public Seller createSeller(Seller seller) throws Exception {
	Seller sellerExist=sellerRepository.findByEmail(seller.getEmail());
	if(sellerExist!=null) {
		
		throw new Exception("seller aready exist, used different email");
	}
	Address savedAddress=addressRepository.save(seller.getPickupAddress());
	Seller newSeller=new Seller();
	newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
	newSeller.setSellerName(seller.getSellerName());
	newSeller.setPickupAddress(savedAddress);
	newSeller.setGSTIN(seller.getGSTIN());
	newSeller.setRole(UserRole.ROLE_SELLER);
	newSeller.setMobile(seller.getMobile());
	newSeller.setBankDetails(seller.getBankDetails());
	newSeller.setBusinessDetails(seller.getBusinessDetails());
	newSeller.setEmail(seller.getEmail());
	return sellerRepository.save(newSeller);
}

@Override
public Seller getSellerById(Long id) throws SellerException {
	
	return sellerRepository.findById(id).orElseThrow(()->new SellerException("seller notfound ith id"+-id));
}

@Override
public Seller getSellerByEmail(String email) throws Exception {
	Seller seller=sellerRepository.findByEmail(email);
	if(seller==null) {
		throw new Exception("seller not found");
		
		
	}
	return  seller;
}

@Override
public List<Seller> getAllSellers(AccountStatus status) {

	return sellerRepository.findByAccountStatus(status);
}

@Override
public Seller updateSeller(Long id, Seller seller) throws Exception {
	
	Seller existSeller=this.getSellerById(id);
	if(seller.getSellerName()!=null) {
		existSeller.setSellerName(seller.getSellerName());
	}
	if(seller.getMobile()!=null) {
		existSeller.setMobile(seller.getMobile());
	}
	if(seller.getEmail()!=null) {
		existSeller.setEmail(seller.getEmail());
	}
	if(seller.getBusinessDetails()!=null&&seller.getBusinessDetails().getBusinessName()!=null) {
		existSeller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
	}
	if(seller.getBankDetails()!=null&&seller.getBankDetails().getAccountHolderName()!=null
			&&seller.getBankDetails().getIfscCode()!=null
			&&seller.getBankDetails().getAccountNumber()!=null) {
		existSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
		existSeller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
		existSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
		
	}
	
	if(seller.getPickupAddress()!=null&&seller.getPickupAddress().getAddress()!=null
			&&seller.getPickupAddress().getMobile()!=null
			&&seller.getPickupAddress().getCity()!=null
			&&seller.getPickupAddress().getState()!=null
			
			) {
		
	existSeller.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
	existSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
	existSeller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
	existSeller.getPickupAddress().setPinCode(seller.getPickupAddress().getPinCode());
	}
	
	if(seller.getGSTIN()!=null) {
		
		existSeller.setGSTIN(seller.getGSTIN());
	}
	return sellerRepository.save(existSeller);
}

@Override
public void deleteSeller(Long id) throws Exception {
	Seller seller=getSellerById(id);
	sellerRepository.delete(seller);
	
	
}

@Override
public Seller verifyEmail(String email, String otp) throws Exception {
	Seller seller=getSellerByEmail(email);
	seller.setEmailVerified(true);
	
	return  sellerRepository.save(seller);
}

@Override
public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {

	Seller seller=getSellerById(sellerId);
	seller.setAccountStatus(status);
	
	return  sellerRepository.save(seller);
	
	
	
	
}

}
