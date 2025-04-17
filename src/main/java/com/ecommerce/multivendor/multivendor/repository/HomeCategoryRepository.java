package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.HomeCategory;
@Repository
public interface HomeCategoryRepository extends JpaRepository<HomeCategory, Long> {

}
