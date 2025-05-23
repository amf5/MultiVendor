package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.eclipse.angus.mail.handlers.handler_base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.domain.OrderStatus;
import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.service.OrderService;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/seller/orders")
public class SellerOrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private SellerService sellerService;
	@GetMapping()
	public ResponseEntity<List<Order>> getAllOrderHandler(@RequestHeader("Authorization") String jwt) throws Exception {
		Seller seller=sellerService.getSellerProfile(jwt);
		List<Order>orders=orderService.sellersOrder(seller.getId());
		return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/{orderId}/status/{orderStatus}")
	public ResponseEntity<Order> updateOrderHandler(@RequestHeader("Authorization")String jwt,
			@PathVariable Long orderId,
			@PathVariable OrderStatus orderStatus)throws Exception{
		
		Order order=orderService.upateOrderStatus(orderId, orderStatus);
		return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
		
		
		
	}

}
