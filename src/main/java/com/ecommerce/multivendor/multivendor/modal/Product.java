package com.ecommerce.multivendor.multivendor.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	
	 private String title;
	 
	 private String description;
	 
	 private int mrpPrice;
	 
	 private int sellingPrice;
	 
	 private int discountPercent;
	 
	 private int quantity;
	 
	 private String color;
	 @ElementCollection
	 private List<String> images=new ArrayList<>();
	 
	 private int numRatings;
	 
	 @ManyToOne
	 private Category category;
	 @ManyToOne
	 private Seller seller;
	 
	 private LocalDateTime createdAt;
	 
	 private String sizes;
	 @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	 private List<Review> reviews= new ArrayList<>();
	@Override
	public int hashCode() {
		return Objects.hash(category, color, createdAt, description, discountPercent, id, images, mrpPrice, numRatings,
				quantity, reviews, seller, sellingPrice, sizes, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(color, other.color)
				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& discountPercent == other.discountPercent && Objects.equals(id, other.id)
				&& Objects.equals(images, other.images) && mrpPrice == other.mrpPrice && numRatings == other.numRatings
				&& quantity == other.quantity && Objects.equals(reviews, other.reviews)
				&& Objects.equals(seller, other.seller) && sellingPrice == other.sellingPrice
				&& Objects.equals(sizes, other.sizes) && Objects.equals(title, other.title);
	}
	public Product() {
		super();
	}
	public Product(Long id, String title, String description, int mrpPrice, int sellingPrice, int discountPercent,
			int quantity, String color, List<String> images, int numRatings, Category category, Seller seller,
			LocalDateTime createdAt, String sizes, List<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.mrpPrice = mrpPrice;
		this.sellingPrice = sellingPrice;
		this.discountPercent = discountPercent;
		this.quantity = quantity;
		this.color = color;
		this.images = images;
		this.numRatings = numRatings;
		this.category = category;
		this.seller = seller;
		this.createdAt = createdAt;
		this.sizes = sizes;
		this.reviews = reviews;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMrpPrice() {
		return mrpPrice;
	}
	public void setMrpPrice(int mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	public int getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public int getNumRatings() {
		return numRatings;
	}
	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getSizes() {
		return sizes;
	}
	public void setSizes(String sizes) {
		this.sizes = sizes;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	 
}
