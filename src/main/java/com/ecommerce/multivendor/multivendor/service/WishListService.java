package com.ecommerce.multivendor.multivendor.service;

import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.modal.WishList;

public interface WishListService {
WishList createWishList(User user);
WishList getWishListByUserId(User user);
WishList addProducttoWishList(User user,Product product);
}
