package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Product;
import com.ecommerce.multivendor.multivendor.modal.Review;
import com.ecommerce.multivendor.multivendor.modal.User;
import com.ecommerce.multivendor.multivendor.request.CreateReviewRequest;
import com.ecommerce.multivendor.multivendor.response.ApiResponse;
import com.ecommerce.multivendor.multivendor.service.ProductService;
import com.ecommerce.multivendor.multivendor.service.ReviewService;
import com.ecommerce.multivendor.multivendor.service.UserService;

@RestController
@RequestMapping("/api")
public class ReviewController {
@Autowired 
private ReviewService reviewService;
@Autowired
private UserService userService;
@Autowired
private ProductService productService;
@GetMapping("/products/{productId}/reviews")
public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable Long productId){
	List<Review> reviews=reviewService.getReviewByProductId(productId);
	return ResponseEntity.ok(reviews);
	
}
@PostMapping("/products/{productId}/reviews")
public ResponseEntity<Review>writeReview(@RequestBody CreateReviewRequest request,
		@PathVariable Long productId,
		@RequestHeader("Authorization")String jwt) throws Exception{
User user=userService.findUserByJwtToken(jwt);
Product product=productService.findProductById(productId);
Review review= reviewService.createReview(request, user, product);
return ResponseEntity.ok(review);

	
	
}
@PatchMapping("/reviews/{reviewId}")
public ResponseEntity<Review>updateReview(@RequestBody CreateReviewRequest request,
		@PathVariable Long reviewId,
		@RequestHeader("Authorization")String jwt) throws Exception{
	User user=userService.findUserByJwtToken(jwt);
	Review review=reviewService.updateReview(reviewId, request.getReviewText(), request.getReviewRating(), user.getId());
	
    return ResponseEntity.ok(review);


}

@DeleteMapping("/reviews/{reviewId}")
public ResponseEntity<ApiResponse>deleteReview(@PathVariable Long reviewId,
		@RequestHeader("Authorization")String jwt)throws Exception{
	
	
	User user=userService.findUserByJwtToken(jwt);
	reviewService.deleteReview(reviewId, user.getId());
	ApiResponse apiResponse=new ApiResponse();
	apiResponse.setMessage("Review deleted successfully");
	return ResponseEntity.ok(apiResponse);
	
	
	}






























}
