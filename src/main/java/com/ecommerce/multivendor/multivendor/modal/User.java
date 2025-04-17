package com.ecommerce.multivendor.multivendor.modal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ecommerce.multivendor.multivendor.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {
 @Id
 @GeneratedValue(strategy =GenerationType.AUTO )
 private Long id;
 @Override
public int hashCode() {
	return Objects.hash(addresses, email, fullName, id, mobile, password, role, usedCoupon);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return Objects.equals(addresses, other.addresses) && Objects.equals(email, other.email)
			&& Objects.equals(fullName, other.fullName) && Objects.equals(id, other.id)
			&& Objects.equals(mobile, other.mobile) && Objects.equals(password, other.password) && role == other.role
			&& Objects.equals(usedCoupon, other.usedCoupon);
}

public User() {
	super();
}

public User(Long id, String password, String email, String fullName, String mobile, UserRole role,
		Set<Address> addresses, Set<Coupon> usedCoupon) {
	super();
	this.id = id;
	this.password = password;
	this.email = email;
	this.fullName = fullName;
	this.mobile = mobile;
	this.role = role;
	this.addresses = addresses;
	this.usedCoupon = usedCoupon;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public UserRole getRole() {
	return role;
}

public void setRole(UserRole role) {
	this.role = role;
}

public Set<Address> getAddresses() {
	return addresses;
}

public void setAddresses(Set<Address> addresses) {
	this.addresses = addresses;
}

public Set<Coupon> getUsedCoupon() {
	return usedCoupon;
}

public void setUsedCoupon(Set<Coupon> usedCoupon) {
	this.usedCoupon = usedCoupon;
}

@JsonProperty(access = Access.WRITE_ONLY)	
 private String password;
 
 private String email;
 
 private String fullName;
 
 private String mobile;
 
 private UserRole role=UserRole.ROLE_CUSTOMER;
 @OneToMany
 private Set<Address>addresses=new HashSet<>();
 @ManyToMany
 @JsonIgnore
 private Set<Coupon> usedCoupon=new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
