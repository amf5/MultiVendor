package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.domain.OrderStatus;
import com.ecommerce.multivendor.multivendor.domain.PaymentStatus;
import com.ecommerce.multivendor.multivendor.modal.Address;
import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.CartItem;
import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.OrderItem;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.AddressRepository;
import com.ecommerce.multivendor.multivendor.repository.OrderItemRepository;
import com.ecommerce.multivendor.multivendor.repository.OrderRepository;
import com.ecommerce.multivendor.multivendor.service.OrderService;

@Service
public class OrderServiceImpl  implements OrderService{
@Autowired
private OrderRepository orderRepository;
@Autowired
private AddressRepository addressRepository;
@Autowired
private OrderItemRepository orderItemRepository;
@Override
	public Set<Order> createOrder(User user, Address ShippingAddress, Cart cart) {
	if(!user.getAddresses().contains(ShippingAddress))	
	{
		user.getAddresses().add(ShippingAddress);
	}
	Address address=addressRepository.save(ShippingAddress);
	//brand 1=>4 shirt
	
	// brand 2=>3 pants
	// brand =>1 watch
	
	Map<Long, List<CartItem>> itemsBySellers=cart.getCartItems().stream().collect(Collectors
			.groupingBy(item->item.getProduct().getSeller().getId()));
	Set<Order> orders=new HashSet<>();
	for(Map.Entry<Long,List<CartItem>> entry:itemsBySellers.entrySet()) {
		Long sellerId=entry.getKey();
		List<CartItem>items=entry.getValue();
		int totalOrderPrice =items.stream().mapToInt(CartItem::getSellingPrice).sum();
		int totalItem=items.stream().mapToInt(CartItem::getQuantity).sum();
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setSellerId(sellerId);
		createdOrder.setTotalMrpPrice(totalOrderPrice);
		createdOrder.setTotalsellingPrice(totalOrderPrice);
		createdOrder.setTotalItem(totalItem);
		createdOrder.setShippingAddres(address);
		createdOrder.setOrderstatus(OrderStatus.PENDING);
		createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		Order savedOrder=orderRepository.save(createdOrder);
		orders.add(savedOrder);
		List<OrderItem> orderItems=new ArrayList<>();
		for(CartItem item:items) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder(savedOrder);
			orderItem.setMrpPrice(item.getMrpPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setSellingPrice(item.getSellingPrice());
			
			savedOrder.getOrderItems().add(orderItem);
			
			
			OrderItem savedOrderItem=orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
	}
	return orders;
	}

	@Override
	public Order findByOrderId(Long id) throws Exception {
	
		return orderRepository.findById(id).orElseThrow(()->new Exception("order not found...."));
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		
		return orderRepository.findByUserId(userId);
	}

	@Override
	public List<Order> sellersOrder(Long sellerId) {
		
		return orderRepository.findBySellerId(sellerId);
	}

	@Override
	public Order upateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
		Order order=findByOrderId(orderId);
		order.setOrderstatus(orderStatus);
		
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(Long orderId, User user) throws Exception {
		Order order=findByOrderId(orderId);
if(!user.getId().equals(order.getUser().getId())) {
	throw new Exception("you don't have excess to this order");
	
}		
		
		order.setOrderstatus(OrderStatus.CANCELED);
		
		return orderRepository.save(order);
	}

	@Override
	public OrderItem getOrderItemById(Long id) throws Exception {
		
		return orderItemRepository.findById(id).orElseThrow(()->new Exception("order item not exist>>>"));
		
	}

}
