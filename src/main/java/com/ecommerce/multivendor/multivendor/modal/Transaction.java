package com.ecommerce.multivendor.multivendor.modal;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {
	 @Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	 
	 @ManyToOne
	 private User customer;
	 
	 @OneToOne
	 private Order order;
	 
	 @ManyToOne
	 private Seller seller;
	 
	 private LocalDateTime date=LocalDateTime.now();

	@Override
	public int hashCode() {
		return Objects.hash(customer, date, id, order, seller);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(order, other.order)
				&& Objects.equals(seller, other.seller);
	}

	public Transaction() {
		super();
	}

	public Transaction(Long id, User customer, Order order, Seller seller, LocalDateTime date) {
		super();
		this.id = id;
		this.customer = customer;
		this.order = order;
		this.seller = seller;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
