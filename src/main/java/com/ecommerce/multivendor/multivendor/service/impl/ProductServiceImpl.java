package com.ecommerce.multivendor.multivendor.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.exception.ProductException;
import com.ecommerce.multivendor.multivendor.modal.Category;
import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.repository.CategoryRepository;
import com.ecommerce.multivendor.multivendor.repository.ProductRepository;
import com.ecommerce.multivendor.multivendor.request.CreateProductRequest;
import com.ecommerce.multivendor.multivendor.service.ProductService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;

@Service
public class ProductServiceImpl implements ProductService{
@Autowired
private ProductRepository productRepository;
@Autowired
private CategoryRepository categoryRepository;
	@Override
	public Product createProduct(CreateProductRequest req, Seller seller) {
		Category category1=categoryRepository.findByCategoryId(req.getCategory());
		if(category1==null) {
			Category category=new Category();
			category.setCategoryId(req.getCategory());
			category.setLevel(1);
			category1=categoryRepository.save(category);
		}
		Category category2=categoryRepository.findByCategoryId(req.getCategory2());
		if(category2==null) {
			Category category=new Category();
			category.setCategoryId(req.getCategory2());
			category.setLevel(2);
			category.setParentCategory(category1);
			category2=categoryRepository.save(category);
		}
	
		Category category3=categoryRepository.findByCategoryId(req.getCategory3());
		if(category3==null) {
			Category category=new Category();
			category.setCategoryId(req.getCategory3());
			category.setLevel(3);
			category.setParentCategory(category2);
			category3=categoryRepository.save(category);
			
			
		}
		int discountPercentage=calculatedDiscountPrecentage( req.getMrpPrice(), req.getSellingPrice());
		Product product=new Product();
		product.setSeller(seller);
		product.setCategory(category3);
		product.setDescription(req.getDescription());
		product.setCreatedAt(LocalDateTime.now());
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setSellingPrice(req.getSellingPrice());
		product.setImages(req.getImages());
		product.setMrpPrice(req.getMrpPrice());
		product.setSizes(req.getSize());
		product.setDiscountPercent(discountPercentage);
		
		return productRepository.save(product);
	}

	private int calculatedDiscountPrecentage(int mrpPrice, int sellingPrice) {
	if(mrpPrice<=0) {
		throw new IllegalArgumentException("Actual price must be grater than 0");
	}
	double discount=mrpPrice-sellingPrice;
	double discountPrecentage=(discount/mrpPrice)*100;
	
		return (int) discountPrecentage;
	}

	@Override
	public void deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
		productRepository.delete(product);
		
	}

	@Override
	public Product updateProduct(Long productId, Product product) throws ProductException {
		findProductById(productId);
		product.setId(productId);
		
		
		
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long productId) throws ProductException {
		
		return productRepository.findById(productId).orElseThrow(()-> new ProductException("product not found with id "+productId));
	}

	

	@Override
	public Page<Product> getAllProducts(String category, String brand, String colors, String sizes, Integer minPrize,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
		Specification< Product> spec=(root,query,criteriaBulider)->{
			List<jakarta.persistence.criteria.Predicate> Predicates=new ArrayList<>();
			if(category!=null) {
				Join<Product, Category> categoryJoin=root.join("category");
				Predicates.add(criteriaBulider.equal(categoryJoin.get("categoryId"), category));
			}
			if(colors!=null&&!colors.isEmpty()) {
				System.out.println("colors "+colors);
				Predicates.add(criteriaBulider.equal(root.get("color"), colors));
			}
			if(sizes!=null&&!sizes.isEmpty()) {
				System.out.println("sizes "+sizes);
				Predicates.add(criteriaBulider.equal(root.get("size"), sizes));
			}if(minPrize!=null) {
				
				Predicates.add(criteriaBulider.greaterThanOrEqualTo(root.get("sellingPrice"), minPrize));
			}
			if(maxPrice!=null) {
				
				Predicates.add(criteriaBulider.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
			}
           if(minDiscount!=null) {
				
				Predicates.add(criteriaBulider.greaterThanOrEqualTo(root.get("discountPrecentage"), minDiscount));
			}
			if(stock!=null) {
				
				Predicates.add(criteriaBulider.equal(root.get("stock"), stock));
			}
			return criteriaBulider.and(Predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
		};
		Pageable pageable;
		if(sort!=null&&!sort.isEmpty()) {
			switch (sort) {
			case "price_low": 
				pageable=PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.by("sellingPrice").ascending());
				break;
			case "price_high": 
				pageable=PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.by("sellingPrice").descending());
				break;
			default:
				pageable=PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.unsorted());
				break;
			
			}
		}else {
			pageable=PageRequest.of(pageNumber!=null?pageNumber:0,10,Sort.unsorted());
		}
		return productRepository.findAll(spec,pageable);
	}

	@Override
	public List<Product> getProductBySellerId(Long sellerId) {
		// TODO Auto-generated method stub
		return productRepository.findBySellerId(sellerId);
	}

	@Override
	public List<Product> searchProducts(String query) {
		// TODO Auto-generated method stub
		return productRepository.searchProduct(query);
	}

	
	
	
}
