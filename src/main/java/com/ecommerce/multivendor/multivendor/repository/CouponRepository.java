package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.Coupon;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
Coupon findByCode(String code);
}
