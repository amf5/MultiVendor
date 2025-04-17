package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.Transaction;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import com.ecommerce.multivendor.multivendor.service.transactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;





@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
@Autowired
private transactionService transactionService;
@Autowired
private SellerService sellerService;





@GetMapping("/seller")
public ResponseEntity<List<Transaction>> getTransactionBySeller(@RequestHeader("Authorization") String jwt) throws Exception {
   
	Seller seller=sellerService.getSellerProfile(jwt);
	List<Transaction> transactions=transactionService.getTransactionsBySellerId(seller);
	
	
	
	return ResponseEntity.ok(transactions);
}





@GetMapping()
public ResponseEntity<List<Transaction>> getAllTransactions() {
	List<Transaction> transactions=transactionService.getAllTransactions();
	return ResponseEntity.ok(transactions);
}


















}
