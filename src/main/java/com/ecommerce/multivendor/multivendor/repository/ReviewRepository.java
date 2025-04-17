package com.ecommerce.multivendor.multivendor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
List<Review> findByProductId(Long ProductId);

}