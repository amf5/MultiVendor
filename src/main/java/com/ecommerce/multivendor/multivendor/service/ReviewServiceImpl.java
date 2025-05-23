package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Review;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.repository.ReviewRepository;
import com.ecommerce.multivendor.multivendor.request.CreateReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService {
@Autowired
private ReviewRepository reviewRepository;

@Override
public Review createReview(CreateReviewRequest req, User user, Product product) {
	Review review=new Review();
	review.setUser(user);
	review.setProduct(product);
	review.setReviewText(req.getReviewText());
	review.setRating(req.getReviewRating());
	review.setProdictImage(req.getProductImages());
	product.getReviews().add(review);
	return reviewRepository.save(review);
}

@Override
public List<Review> getReviewByProductId(Long productId) {
	
	return reviewRepository.findByProductId(productId);
}

@Override
public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
	Review review=getReviewById(reviewId);
	if(review.getUser().getId().equals(userId)) {
		review.setReviewText(reviewText);
		review.setRating(rating);
		return reviewRepository.save(review);
		
	}
	
	throw new Exception("you can't update this review");
}

@Override
public void deleteReview(Long reviewId, Long userId) throws Exception {
	Review review=getReviewById(reviewId);
	if(review.getUser().getId().equals(userId)) {
		throw new Exception("you can't delete this review");
	}
	reviewRepository.delete(review);
}

@Override
public Review getReviewById(Long reviewId) throws Exception {
	
	return reviewRepository.findById(reviewId).orElseThrow(()->new Exception("review not found"));
}

}
