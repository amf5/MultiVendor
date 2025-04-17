package com.ecommerce.multivendor.multivendor.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.Coupon;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.CartRepository;
import com.ecommerce.multivendor.multivendor.repository.CouponRepository;
import com.ecommerce.multivendor.multivendor.repository.UserRepository;
import com.ecommerce.multivendor.multivendor.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService{
@Autowired
private CouponRepository couponRepository;
@Autowired
private CartRepository cartRepository;
@Autowired
private UserRepository userRepository;
	@Override
	public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
	Coupon coupon=couponRepository.findByCode(code);
	Cart cart=cartRepository.findByUserId(user.getId());
	if(coupon==null) {
		throw new Exception("coupon not valid");
	}
	if(user.getUsedCoupon().contains(coupon)) {
		throw new Exception("coupon aready used");
	}
	if(orderValue<coupon.getMinimumOrderValue()) {
		throw new Exception ("valid for mimimim order value"+coupon.getMinimumOrderValue());
	}if(coupon.isActive()&&LocalDate.now().isAfter(coupon.getValidityStartDate())
			&&LocalDate.now().isBefore(coupon.getValidityEndDate())) {
		user.getUsedCoupon().add(coupon);
		userRepository.save(user);
		double discountedPrice= (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
		cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountedPrice);
		cart.setCouponCode(code);
		cartRepository.save(cart);
		return cart;
	}
		throw new Exception("coupon not valid");
	}

	@Override
	public Cart removeCoupon(String code, User user) throws Exception {
		Coupon coupon=couponRepository.findByCode(code);
		if(coupon==null) {
			throw new Exception("coupon not found.....");	
		}
		Cart cart=cartRepository.findByUserId(user.getId());
		double discountedPrice= (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
		cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountedPrice);
		cart.setCouponCode(null);
	
		return cartRepository.save(cart);
	}

	@Override
	public Coupon findCouponById(Long id) throws Exception {
		
		return couponRepository.findById(id).orElseThrow(()->new Exception("coupon not found"));
	}

	@Override
	
	public Coupon createCoupon(Coupon coupon) {
		
		return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupons() {
	
		return couponRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {
		findCouponById(id);
	couponRepository.deleteById(id);
		
	}

}
