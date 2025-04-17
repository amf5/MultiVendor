package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.exception.ProductException;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.request.CreateProductRequest;
import com.ecommerce.multivendor.multivendor.service.ProductService;
import com.ecommerce.multivendor.multivendor.service.SellerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/sellers/products")
public class SellerProductController {
@Autowired	
private ProductService productService;
@Autowired	
private SellerService sellerService;
@GetMapping()
public ResponseEntity<List<Product>> getProductBySellerId(@RequestHeader("Authorization") String jwt)throws Exception{
	Seller seller=sellerService.getSellerProfile(jwt);
	List<Product> products=productService.getProductBySellerId(seller.getId());
	return new ResponseEntity<>(products,HttpStatus.OK);
	
	
	
}



@PostMapping()
public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request,
		@RequestHeader("Authorization") String jwt)throws Exception ,ProductException,SecurityException{
    Seller seller=sellerService.getSellerProfile(jwt);
    Product product=productService.createProduct(request, seller);
    return new ResponseEntity<>(product,HttpStatus.CREATED);
}




@DeleteMapping("/{productId}")
public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
	try {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	} catch (ProductException e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
}

@PutMapping("/{productId}")
public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product) throws ProductException{
	
		Product updatedProduct=productService.updateProduct(productId,product);
		return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
	
	
	
}




























}
