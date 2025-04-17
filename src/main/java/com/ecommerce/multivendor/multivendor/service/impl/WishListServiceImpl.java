package com.ecommerce.multivendor.multivendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.modal.WishList;
import com.ecommerce.multivendor.multivendor.repository.WishListRepository;
import com.ecommerce.multivendor.multivendor.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService {
@Autowired
private  WishListRepository wishListRepository;


	@Override
	public WishList createWishList(User user) {
		WishList wishList=new WishList();
		wishList.setUser(user);
		
		return wishListRepository.save(wishList);
	}

	@Override
	public WishList getWishListByUserId(User user) {
		
		WishList wishList= wishListRepository.findByUser(user.getId());
		if(wishList==null) {
			wishList=createWishList(user);
			
		}
		return wishList;
	}

	@Override
	public WishList addProducttoWishList(User user, Product product) {
		WishList wishList=getWishListByUserId(user);
		if(wishList.getProducts().contains(product)) {
			wishList.getProducts().remove(product);
		}else {
			wishList.getProducts().add(product);
		}
		
		return wishListRepository.save(wishList);
	}

}
