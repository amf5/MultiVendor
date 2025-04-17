package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.exception.ProductException;
import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.request.AddItemRequest;
import com.ecommerce.multivendor.multivendor.response.ApiResponse;
import com.ecommerce.multivendor.multivendor.service.CartItemService;
import com.ecommerce.multivendor.multivendor.service.CartService;
import com.ecommerce.multivendor.multivendor.service.ProductService;
import com.ecommerce.multivendor.multivendor.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
@Autowired
private CartService cartService;
@Autowired
private CartItemService cartItemService;
@Autowired
private UserService userService;
@Autowired
private ProductService productService;
@GetMapping
public ResponseEntity< Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws Exception,ProductException{
	User user=userService.findUserByJwtToken(jwt);
	Cart cart=cartService.findUserCart(user);
	return new ResponseEntity<>(cart,HttpStatus.OK);
	
	
}
@PutMapping("/add")
public ResponseEntity<ApiResponse> addItemToCart(@RequestHeader("Authorization") String jwt,
		@RequestBody AddItemRequest req) throws Exception{
	User user=userService.findUserByJwtToken(jwt);
	Product product=productService.findProductById(req.getProductId());
	CartItem item=cartService.addCartItem(user,
			product, 
			req.getSize(),
			req.getQuantity());
	
	ApiResponse response=new ApiResponse();
	response.setMessage("\"Item Added TO cart Successfully\"");
	
	return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}


@DeleteMapping("/item/{cartItemId}")
public ResponseEntity<ApiResponse>deleteCartItemHandler(@PathVariable Long cartItemId
		,@RequestHeader("Authorization")String jwt) throws Exception{
	User user=userService.findUserByJwtToken(jwt);
	cartItemService.removeCartItem(user.getId(), cartItemId);
	ApiResponse response=new ApiResponse();
	response.setMessage("\"Item remove From cart Successfully\"");
	return new ResponseEntity<>(response,HttpStatus.ACCEPTED);	
}

@PutMapping("/item/{cartItemId}")
public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId
		                   , @RequestBody CartItem cartItem
		                   ,@RequestHeader("Authorization") String jwt) throws Exception {
   User user=userService.findUserByJwtToken(jwt);
   CartItem updatedCartItem=null;
   if(cartItem.getQuantity()>0) {
	   updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
   }
    
    return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED) ;
    
}












}
