package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.domain.PaymentMethod;
import com.ecommerce.multivendor.multivendor.modal.Address;
import com.ecommerce.multivendor.multivendor.modal.Cart;
import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.OrderItem;
import com.ecommerce.multivendor.multivendor.modal.PaymentOrder;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.SellerReport;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.PaymentOrderRepository;
import com.ecommerce.multivendor.multivendor.response.PaymentLinkResponse;
import com.ecommerce.multivendor.multivendor.service.CartService;
import com.ecommerce.multivendor.multivendor.service.OrderService;
import com.ecommerce.multivendor.multivendor.service.PaymentService;
import com.ecommerce.multivendor.multivendor.service.SellerReportService;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import com.ecommerce.multivendor.multivendor.service.UserService;
import com.razorpay.PaymentLink;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/orders")
public class OrderController {
@Autowired
private OrderService orderService;
@Autowired
private UserService userService;
@Autowired
private CartService cartService;
@Autowired
private SellerService sellerService;
@Autowired
private SellerReportService sellerReportService;
@Autowired
private PaymentService paymentService;
@Autowired
private PaymentOrderRepository paymentOrderRepository;

@PostMapping()
public ResponseEntity<PaymentLinkResponse> createOrderHander(@RequestBody Address shipAddress,
		@RequestParam PaymentMethod paymentMethod,
		@RequestHeader("Authorization") String jwt)throws Exception{
	User user =userService.findUserByJwtToken(jwt);
	Cart cart=cartService.findUserCart(user);
	Set<Order>orders=orderService.createOrder(user, shipAddress, cart);
	PaymentOrder paymentOrder=paymentService.createOrder(user,orders);
	PaymentLinkResponse res=new PaymentLinkResponse();
	if(paymentMethod.equals(PaymentMethod.RAZORPAY)) {
		PaymentLink payment=paymentService.createRAzorpayPaymenetLink(user,paymentOrder.getAmount(),paymentOrder.getId());
		String  paymentUrl=payment.get("short_url");
		String paymentUrlId=payment.get("id");
		res.setPayment_link_url(paymentUrlId);
		paymentOrderRepository.save(paymentOrder);
		
	}else {
		
		String paymentUrl=paymentService.createStripePaymentLink(user,paymentOrder.getAmount(),paymentOrder.getId());
		res.setPayment_link_url(paymentUrl);
	}
	return new ResponseEntity<PaymentLinkResponse>(res,HttpStatus.OK);
	
	
}
@GetMapping("/user")
public ResponseEntity<List<Order>> usersOrderHistoryHandler(@RequestHeader ("Authorization")String jwt) throws Exception{
   
	User user=userService.findUserByJwtToken(jwt);
	List<Order>orders=orderService.userOrderHistory(user.getId());

	return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
}


@GetMapping("/{orderId}")
public ResponseEntity<OrderItem> getOrderById(@PathVariable Long orderId,
		@RequestHeader("Authorization")String jwt)throws Exception {
	User user=userService.findUserByJwtToken(jwt);
	OrderItem orderItem=orderService.getOrderItemById(orderId);
	
    return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
}


@PutMapping("/{orderId}/cancel")
public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId
		,@RequestHeader("Authorization")String jwt ) throws Exception {
	User user=userService.findUserByJwtToken(jwt);
	Order order=orderService.cancelOrder(orderId, user);
	Seller seller=sellerService.getSellerById(orderId);
	SellerReport report=sellerReportService.getSellerReport(seller);
	report.setCancledOrders(report.getCancledOrders()+1);
	report.setTotalRefunds(report.getTotalRefunds()+order.getTotalsellingPrice());
	sellerReportService.updateSellerReport(report);
    return ResponseEntity.ok(order);
}















}
