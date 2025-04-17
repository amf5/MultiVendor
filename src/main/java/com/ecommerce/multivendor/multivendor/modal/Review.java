package com.ecommerce.multivendor.multivendor.modal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	
	@Column(nullable = false)
	private String reviewText;
	@Column(nullable = false)
	private double rating;
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, id, prodictImage, product, rating, reviewText, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
				&& Objects.equals(prodictImage, other.prodictImage) && Objects.equals(product, other.product)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(reviewText, other.reviewText) && Objects.equals(user, other.user);
	}
	public Review() {
		super();
	}
	public Review(Long id, String reviewText, double rating, List<String> prodictImage, Product product, User user,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.reviewText = reviewText;
		this.rating = rating;
		this.prodictImage = prodictImage;
		this.product = product;
		this.user = user;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public List<String> getProdictImage() {
		return prodictImage;
	}
	public void setProdictImage(List<String> prodictImage) {
		this.prodictImage = prodictImage;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@ElementCollection
	private List<String > prodictImage;
	@JsonIgnore
	@ManyToOne

	private Product product;
	@ManyToOne
	
	private User user;
	
	private LocalDateTime createdAt=LocalDateTime.now();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
