package com.ecommerce.multivendor.multivendor.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ecommerce.multivendor.multivendor.domain.OrderStatus;
import com.ecommerce.multivendor.multivendor.domain.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders_table")
public class Order {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	
	private String orderId;
	
	@ManyToOne
	private User user;
	
	private Long sellerId;
	
	@OneToMany(mappedBy = "order",cascade =CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem> orderItems=new ArrayList<>();
	
	@ManyToOne
	private Address shippingAddres;
	
	@Embedded
	private  PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalMrpPrice;
	
	private Integer totalsellingPrice;
	private OrderStatus orderstatus;
	private int totalItem;
	private PaymentStatus paymentStatus=PaymentStatus.PENDING;
	
	@Override
	public int hashCode() {
		return Objects.hash(deliveryDate, id, orderDate, orderId, orderItems, orderstatus, paymentDetails,
				paymentStatus, sellerId, shippingAddres, totalItem, totalMrpPrice, totalsellingPrice, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(deliveryDate, other.deliveryDate) && Objects.equals(id, other.id)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItems, other.orderItems) && orderstatus == other.orderstatus
				&& Objects.equals(paymentDetails, other.paymentDetails) && paymentStatus == other.paymentStatus
				&& Objects.equals(sellerId, other.sellerId) && Objects.equals(shippingAddres, other.shippingAddres)
				&& totalItem == other.totalItem
				&& Double.doubleToLongBits(totalMrpPrice) == Double.doubleToLongBits(other.totalMrpPrice)
				&& Objects.equals(totalsellingPrice, other.totalsellingPrice) && Objects.equals(user, other.user);
	}
	public Order() {
		super();
	}
	public Order(Long id, String orderId, User user, Long sellerId, List<OrderItem> orderItems, Address shippingAddres,
			PaymentDetails paymentDetails, double totalMrpPrice, Integer totalsellingPrice, OrderStatus orderstatus,
			int totalItem, PaymentStatus paymentStatus, LocalDateTime orderDate, LocalDateTime deliveryDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.sellerId = sellerId;
		this.orderItems = orderItems;
		this.shippingAddres = shippingAddres;
		this.paymentDetails = paymentDetails;
		this.totalMrpPrice = totalMrpPrice;
		this.totalsellingPrice = totalsellingPrice;
		this.orderstatus = orderstatus;
		this.totalItem = totalItem;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Address getShippingAddres() {
		return shippingAddres;
	}
	public void setShippingAddres(Address shippingAddres) {
		this.shippingAddres = shippingAddres;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public double getTotalMrpPrice() {
		return totalMrpPrice;
	}
	public void setTotalMrpPrice(double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}
	public Integer getTotalsellingPrice() {
		return totalsellingPrice;
	}
	public void setTotalsellingPrice(Integer totalsellingPrice) {
		this.totalsellingPrice = totalsellingPrice;
	}
	public OrderStatus getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	public int getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	private LocalDateTime orderDate=LocalDateTime.now();
	private LocalDateTime deliveryDate=orderDate.plusDays(7);
	
	
	
	
	
}
