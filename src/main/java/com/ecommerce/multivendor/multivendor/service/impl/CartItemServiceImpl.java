package com.ecommerce.multivendor.multivendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.CartItemRepository;
import com.ecommerce.multivendor.multivendor.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
@Autowired
private   CartItemRepository cartItemRepository;

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
		CartItem item=findCartItemById(id);
		User cartItemUser=item.getCart().getUser();
		if(cartItemUser.getId().equals(userId)) {
			
			item.setQuantity(item.getQuantity()+cartItem.getQuantity());
			item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
			item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
			return cartItemRepository.save(item);
			
		}
		
		throw new Exception("you can't update this cartItem");
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws Exception {
		CartItem item=findCartItemById(cartItemId);
         User cartItemUser=item.getCart().getUser();
         if(cartItemUser.getId().equals(userId)) {
        	 cartItemRepository.delete(item);
        	  }
         throw new Exception("you can't delete this cartItem");
		
	}

	@Override
	public CartItem findCartItemById(Long id) throws Exception {
		
		return cartItemRepository.findById(id).orElseThrow(()->new Exception("cart item not found with id"+id));
	}

}
