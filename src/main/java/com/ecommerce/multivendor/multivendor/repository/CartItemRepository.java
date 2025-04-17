package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


CartItem findByCartAndProductAndQuantity(Cart cart, Product product, int quantity);
}
