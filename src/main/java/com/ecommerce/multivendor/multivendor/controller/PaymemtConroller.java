package com.ecommerce.multivendor.multivendor.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.PaymentOrder;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.SellerReport;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.response.ApiResponse;
import com.ecommerce.multivendor.multivendor.response.PaymentLinkResponse;
import com.ecommerce.multivendor.multivendor.service.PaymentService;
import com.ecommerce.multivendor.multivendor.service.SellerReportService;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import com.ecommerce.multivendor.multivendor.service.UserService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/payment")
public class PaymemtConroller {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private SellerReportService sellerReportService;
	@Autowired
	private com.ecommerce.multivendor.multivendor.service.transactionService transactionService;
	
	
@GetMapping("/{paymentId}")
public ResponseEntity<ApiResponse> PaymentSuccessHandeler(@PathVariable String PaymentId,
		@RequestParam String paymentLinkId,
		@RequestHeader("Authorization") String jwt)throws Exception {
	User user=userService.findUserByJwtToken(jwt);
	PaymentLinkResponse paymentLinkResponse;
	PaymentOrder paymentOrder=paymentService.getPaymentOrderByPaymentId(paymentLinkId);
	boolean paymentSucess=paymentService.ProceedPaymentOrder(paymentOrder, PaymentId, paymentLinkId);
	if(paymentSucess) {
		for(Order order:paymentOrder.getOrders()) {
			transactionService.createTransaction(order);
			Seller seller=sellerService.getSellerById(order.getSellerId());
			SellerReport report=sellerReportService.getSellerReport(seller);
			report.setTotalsOrders(report.getTotalsOrders()+1);
			report.setTotalEarnings(report.getTotalEarnings()+order.getTotalsellingPrice());
			report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
			sellerReportService.updateSellerReport(report);
		}
	}
	
	ApiResponse response=new  ApiResponse();
	response.setMessage("paymentSucessful");
	
    return new ResponseEntity(response,HttpStatus.CREATED);
}

}
