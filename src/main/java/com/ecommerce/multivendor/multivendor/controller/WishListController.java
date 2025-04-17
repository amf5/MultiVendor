package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.exception.ProductException;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.modal.WishList;
import com.ecommerce.multivendor.multivendor.service.ProductService;
import com.ecommerce.multivendor.multivendor.service.UserService;
import com.ecommerce.multivendor.multivendor.service.WishListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
	@Autowired
	private WishListService wishListService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@GetMapping()
	public ResponseEntity<WishList> getWishListByUserId(@RequestHeader("Authorization")String jwt)throws Exception {
		
		User user=userService.findUserByJwtToken(jwt);
		WishList wishList=wishListService.getWishListByUserId(user);
		
		return ResponseEntity.ok(wishList);
	}
	
	@PostMapping("/add-product/{productId}")
	public ResponseEntity<WishList>addProductToWishList(@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt)throws ProductException,Exception{
		Product product=productService.findProductById(productId);
		User user=userService.findUserByJwtToken(jwt);
		WishList updateWishList=wishListService.addProducttoWishList(user, product);
		return ResponseEntity.ok(updateWishList);
	}
	

}
