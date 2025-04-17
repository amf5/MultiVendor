package com.ecommerce.multivendor.multivendor.modal;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.ecommerce.multivendor.multivendor.domain.PaymentMethod;
import com.ecommerce.multivendor.multivendor.domain.PaymentOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class PaymentOrder {
	 @Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	 private Long amount;
	 private PaymentOrderStatus status=PaymentOrderStatus.PENDING;
	 private PaymentMethod paymentMethod;
	 private String PaymentLinkId;
	 @ManyToOne
	 private User user;
	 @OneToMany
	 private Set<Order> orders=new HashSet<>();
	@Override
	public int hashCode() {
		return Objects.hash(PaymentLinkId, amount, id, orders, paymentMethod, status, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentOrder other = (PaymentOrder) obj;
		return Objects.equals(PaymentLinkId, other.PaymentLinkId) && Objects.equals(amount, other.amount)
				&& Objects.equals(id, other.id) && Objects.equals(orders, other.orders)
				&& paymentMethod == other.paymentMethod && status == other.status && Objects.equals(user, other.user);
	}
	public PaymentOrder() {
		super();
	}
	public PaymentOrder(Long id, Long amount, PaymentOrderStatus status, PaymentMethod paymentMethod,
			String paymentLinkId, User user, Set<Order> orders) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.paymentMethod = paymentMethod;
		PaymentLinkId = paymentLinkId;
		this.user = user;
		this.orders = orders;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public PaymentOrderStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentOrderStatus status) {
		this.status = status;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentLinkId() {
		return PaymentLinkId;
	}
	public void setPaymentLinkId(String paymentLinkId) {
		PaymentLinkId = paymentLinkId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}	 
}
