package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.SellerRepository;
import com.ecommerce.multivendor.multivendor.repository.UserRepository;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    private static final String SELLER_PREFIX = "seller_";

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith(SELLER_PREFIX)) {
            String actualUser = username.substring(SELLER_PREFIX.length());
            Seller seller = sellerRepository.findByEmail(actualUser);
            if (seller != null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
        } else {
            User user = repository.findByEmail(username);
            System.out.println("email"+username);
            if (user != null) {
            	 System.out.println("email"+username+"true");
                return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }
        System.out.println("email"+username+"false");
        throw new UsernameNotFoundException("User or seller not found with email: " + username);
    }

    private UserDetails buildUserDetails(String email, String password, UserRole role) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if (role == null) {
            role = UserRole.ROLE_CUSTOMER; // تعيين دور افتراضي إذا لم يكن محددًا
        }

        authorityList.add(new SimpleGrantedAuthority(role.name())); // استخدام name() بدلًا من toString()

        return new org.springframework.security.core.userdetails.User(email, password, authorityList);
    }
}


