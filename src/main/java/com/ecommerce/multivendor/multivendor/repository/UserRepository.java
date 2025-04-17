package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
User findByEmail(String email);
}
