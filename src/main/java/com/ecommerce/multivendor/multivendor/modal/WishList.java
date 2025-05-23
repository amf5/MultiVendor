package com.ecommerce.multivendor.multivendor.modal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class WishList {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	@OneToOne
	private User user;
	@ManyToMany
	private Set<Product> products=new HashSet<>();
	@Override
	public int hashCode() {
		return Objects.hash(id, products, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishList other = (WishList) obj;
		return Objects.equals(id, other.id) && Objects.equals(products, other.products)
				&& Objects.equals(user, other.user);
	}
	public WishList() {
		super();
	}
	public WishList(Long id, User user, Set<Product> products) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
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
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
