package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.multivendor.multivendor.modal.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
Category findByCategoryId(String categoryId);
}
