package com.ecommerce.multivendor.multivendor.modal;

import com.ecommerce.multivendor.multivendor.domain.PaymentStatus;

import jakarta.persistence.Embeddable;
@Embeddable
public class PaymentDetails {
public PaymentDetails() {
		super();
	}
public PaymentDetails(String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkReferenceId,
			String razoypayPaymentLinkStatus, String razopayPaymentId, PaymentStatus status) {
		super();
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
		this.razoypayPaymentLinkStatus = razoypayPaymentLinkStatus;
		this.razopayPaymentId = razopayPaymentId;
		this.status = status;
	}
public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}
	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}
	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}
	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}
	public String getRazoypayPaymentLinkStatus() {
		return razoypayPaymentLinkStatus;
	}
	public void setRazoypayPaymentLinkStatus(String razoypayPaymentLinkStatus) {
		this.razoypayPaymentLinkStatus = razoypayPaymentLinkStatus;
	}
	public String getRazopayPaymentId() {
		return razopayPaymentId;
	}
	public void setRazopayPaymentId(String razopayPaymentId) {
		this.razopayPaymentId = razopayPaymentId;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
private String paymentId;
private String razorpayPaymentLinkId;
private String razorpayPaymentLinkReferenceId;
private String razoypayPaymentLinkStatus;
private String razopayPaymentId;
private PaymentStatus status;
}
