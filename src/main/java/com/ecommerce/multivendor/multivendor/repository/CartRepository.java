package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.multivendor.multivendor.modal.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
Cart findByUserId(Long id);
}
