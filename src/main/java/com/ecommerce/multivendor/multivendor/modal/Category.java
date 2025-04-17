package com.ecommerce.multivendor.multivendor.modal;

import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	
	private String name;
	

	public Category() {
		super();
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, id, level, name, parentCategory);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(categoryId, other.categoryId) && Objects.equals(id, other.id)
				&& Objects.equals(level, other.level) && Objects.equals(name, other.name)
				&& Objects.equals(parentCategory, other.parentCategory);
	}



	public Category(Long id, String name, String categoryId, Category parentCategory, Integer level) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.parentCategory = parentCategory;
		this.level = level;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@NotNull
	@Column(unique = true)
	private String categoryId;
	
	@ManyToOne
	private Category parentCategory;
	
	@NotNull
	private Integer level;
}
