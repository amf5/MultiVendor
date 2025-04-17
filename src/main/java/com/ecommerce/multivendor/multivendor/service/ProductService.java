package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerce.multivendor.multivendor.exception.ProductException;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.request.CreateProductRequest;

public interface ProductService {
public Product createProduct(CreateProductRequest req,Seller seller); 
public void deleteProduct(Long productId) throws ProductException;
public Product updateProduct(Long productId,Product product) throws ProductException;
Product findProductById(Long productId) throws ProductException;

public Page<Product> getAllProducts(String category,String brand,String colors,String sizes
		,Integer minPrize,Integer maxPrice,Integer minDiscount,String sort
		,String stock,Integer pageNumber);
List<Product> getProductBySellerId(Long sellerId);
List<Product> searchProducts(String query);

}
