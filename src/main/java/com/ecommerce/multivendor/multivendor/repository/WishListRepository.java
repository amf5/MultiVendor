package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>{
WishList findByUser(Long id);

}
