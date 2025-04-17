package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.Coupon;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.service.CartService;
import com.ecommerce.multivendor.multivendor.service.CouponService;
import com.ecommerce.multivendor.multivendor.service.UserService;


@RestController
@RequestMapping("/api/coupons")
public class AdminCouponController {
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/apply")
	public ResponseEntity<Cart>applyCoupon(@RequestParam String apply,
			@RequestParam String code,
			@RequestParam double orderValue,
			@RequestHeader("Authorization")String jwt)throws Exception{
		User user=userService.findUserByJwtToken(jwt);
		Cart cart;
		if(apply.equals("true")) {
			cart=couponService.applyCoupon(code, orderValue, user);
		}else {
			cart=couponService.removeCoupon(code, user);
		}
		
		return ResponseEntity.ok(cart);
	}
	
	
	@PostMapping("/admin/create")
	public ResponseEntity<Coupon>createCoupon(@RequestBody Coupon coupon){
		Coupon createdCoupon=couponService.createCoupon(coupon);

		return ResponseEntity.ok(createdCoupon);
	}
	
	@PatchMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable Long id) throws Exception{
		couponService.deleteCoupon(id);
		return ResponseEntity.ok("Coupon deletedSucessfully");
	}
	@GetMapping("/admin/all")
	public ResponseEntity<List<Coupon>> getAllCoupons(){
		List<Coupon>coupons=couponService.findAllCoupons();
		return ResponseEntity.ok(coupons);
	}
	
	
	
	
	
	
	
	
	
	
	

}
