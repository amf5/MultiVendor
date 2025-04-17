package com.ecommerce.multivendor.multivendor.service;

import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;

public interface CartService {
	public CartItem addCartItem(User user,Product product,String size,int Quantity);
	public Cart findUserCart(User user);
}
