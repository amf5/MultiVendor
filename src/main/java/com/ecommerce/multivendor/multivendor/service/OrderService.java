package com.ecommerce.multivendor.multivendor.service;

import java.util.List;
import java.util.Set;

import com.ecommerce.multivendor.multivendor.domain.OrderStatus;
import com.ecommerce.multivendor.multivendor.modal.Address;
import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.OrderItem;
import com.ecommerce.multivendor.multivendor.modal.User;

public interface OrderService {
Set<Order> createOrder(User user, Address ShippingAddress,Cart cart);
Order findByOrderId(Long id) throws Exception;
List<Order>userOrderHistory(Long userId);
List<Order>sellersOrder(Long sellerId);
Order upateOrderStatus(Long orderId,OrderStatus orderStatus) throws Exception;
Order cancelOrder(Long orderId,User user) throws Exception;
OrderItem getOrderItemById(Long id) throws Exception;
}
