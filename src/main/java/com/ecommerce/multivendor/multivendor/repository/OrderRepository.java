package com.ecommerce.multivendor.multivendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
List<Order>findByUserId(Long UserId);
List<Order>findBySellerId(Long SellerId);
}
