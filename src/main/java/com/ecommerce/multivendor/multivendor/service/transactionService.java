package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.Transaction;

public interface transactionService {
Transaction createTransaction(Order order );
List<Transaction> getTransactionsBySellerId(Seller seller);
List<Transaction> getAllTransactions();

}
