package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Review;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.request.CreateReviewRequest;

public interface ReviewService {
Review  createReview(CreateReviewRequest req,User user,Product product);
List<Review>getReviewByProductId(Long productId);
Review updateReview(Long reviewId,String reviewText,double rating ,Long userId) throws Exception;
void deleteReview(Long reviewId,Long userId) throws Exception;
Review getReviewById(Long reviewId) throws Exception;

	
	
	
	
	
	
	
	
	
	
	
	
	
}
