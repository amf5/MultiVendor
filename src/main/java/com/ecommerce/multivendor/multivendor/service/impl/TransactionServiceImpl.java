package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.Transaction;
import com.ecommerce.multivendor.multivendor.repository.SellerRepository;
import com.ecommerce.multivendor.multivendor.repository.TransactionRepository;
import com.ecommerce.multivendor.multivendor.service.transactionService;

@Service
public class TransactionServiceImpl implements transactionService {
@Autowired
private TransactionRepository transactionRepository;
@Autowired
private SellerRepository sellerRepository;
	@Override
	public Transaction createTransaction(Order order) {
		Seller seller=sellerRepository.findById(order.getSellerId()).get();
		Transaction transaction=new Transaction();
		transaction.setSeller(seller);
		transaction.setCustomer(order.getUser());
		transaction.setOrder(order);
		
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionsBySellerId(Seller seller) {
	
		return transactionRepository.findBySellerId(seller.getId());
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
	}


}
