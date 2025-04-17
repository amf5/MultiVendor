package com.ecommerce.multivendor.multivendor.modal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.aspectj.weaver.tools.Trace;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
     @OneToOne	
	 private User user;
     @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval =true)
     private Set<CartItem> cartItems=new HashSet<>();
     
     private double totalSellingPrice;
     
     private int totalItem;
     
     private int totalMrpPrice;
     
     private int discount;
     
     private String couponCode;

	@Override
	public int hashCode() {
		return Objects.hash(cartItems, couponCode, discount, id, totalItem, totalMrpPrice, totalSellingPrice, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(cartItems, other.cartItems) && Objects.equals(couponCode, other.couponCode)
				&& discount == other.discount && Objects.equals(id, other.id) && totalItem == other.totalItem
				&& totalMrpPrice == other.totalMrpPrice
				&& Double.doubleToLongBits(totalSellingPrice) == Double.doubleToLongBits(other.totalSellingPrice)
				&& Objects.equals(user, other.user);
	}

	public Cart() {
		super();
	}

	public Cart(Long id, User user, Set<CartItem> cartItems, double totalSellingPrice, int totalItem, int totalMrpPrice,
			int discount, String couponCode) {
		super();
		this.id = id;
		this.user = user;
		this.cartItems = cartItems;
		this.totalSellingPrice = totalSellingPrice;
		this.totalItem = totalItem;
		this.totalMrpPrice = totalMrpPrice;
		this.discount = discount;
		this.couponCode = couponCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(int totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}
