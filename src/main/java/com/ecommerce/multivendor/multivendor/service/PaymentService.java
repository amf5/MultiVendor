package com.ecommerce.multivendor.multivendor.service;

import java.util.Set;

import com.ecommerce.multivendor.multivendor.modal.Order;
import com.ecommerce.multivendor.multivendor.modal.PaymentOrder;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
PaymentOrder createOrder(User user,Set<Order>orders);
PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
boolean ProceedPaymentOrder(PaymentOrder paymentOrder,String PaymentId,String PaymentLinkId) throws RazorpayException;
PaymentLink createRAzorpayPaymenetLink(User user,Long amount,Long OrderId) throws RazorpayException;
String createStripePaymentLink(User user,Long amount,Long orderId) throws StripeException;
}
