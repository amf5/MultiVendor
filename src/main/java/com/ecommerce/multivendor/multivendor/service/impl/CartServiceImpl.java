package com.ecommerce.multivendor.multivendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.CartItemRepository;
import com.ecommerce.multivendor.multivendor.repository.CartRepository;
import com.ecommerce.multivendor.multivendor.service.CartService;

@Service
public class CartServiceImpl implements CartService {
@Autowired
private CartRepository cartRepository;
@Autowired
private CartItemRepository cartItemRepository;
@Override
public CartItem addCartItem(User user, Product product, String size, int Quantity) {
Cart cart=findUserCart(user);

CartItem isPresent=cartItemRepository.findByCartAndProductAndQuantity(cart, product, Quantity);

if(isPresent==null) {
	CartItem cartItem=new CartItem();
	cartItem.setProduct(product);
	cartItem.setQuantity(Quantity);
	cartItem.setId(user.getId());
	cartItem.setSize(size);
	int totalPrice=Quantity*product.getSellingPrice();
	cartItem.setSellingPrice(totalPrice);
	cartItem.setMrpPrice(Quantity*product.getMrpPrice());
	cart.getCartItems().add(cartItem);
	cartItem.setCart(cart);
	
	return cartItemRepository.save(cartItem);
	
	
}

	return isPresent;
}
@Override
public Cart findUserCart(User user) {
	Cart cart=cartRepository.findByUserId(user.getId());
	
	int totalPrice=0;
	int totalDiscountedPrice=0;
	int totalItem=0;
	for(CartItem  cartItem:cart.getCartItems()) {
		totalPrice+=cartItem.getMrpPrice();
		totalDiscountedPrice+=cartItem.getSellingPrice();
		totalItem+=cartItem.getQuantity();
	}
	cart.setTotalMrpPrice(totalPrice);
	cart.setTotalItem(totalItem);
	cart.setTotalSellingPrice(totalDiscountedPrice);
	cart.setDiscount(calculateDiscountPercentage( totalItem,totalDiscountedPrice));
	cart.setTotalItem(totalItem);
	return cart;
}

private int calculateDiscountPercentage(int mrpPrice,int sellingPrice) {
	if(mrpPrice<=0) {
		
	}
	double discount=mrpPrice-sellingPrice;
	double discountPerecentage=(discount/mrpPrice)*100;
	return (int) discountPerecentage;
}
}
