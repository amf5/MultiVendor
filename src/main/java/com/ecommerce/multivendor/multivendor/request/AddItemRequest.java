package com.ecommerce.multivendor.multivendor.request;

public class AddItemRequest {
private Long productId;	
public AddItemRequest() {
	super();
}

public AddItemRequest(Long productId, String size, int quantity) {
	super();
	this.productId = productId;
	this.size = size;
	this.quantity = quantity;
}

public Long getProductId() {
	return productId;
}

public void setProductId(Long productId) {
	this.productId = productId;
}

public String getSize() {
	return size;
}

public void setSize(String size) {
	this.size = size;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

private String size;

private int quantity;
}
