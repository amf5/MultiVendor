package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.HomeCategory;
import com.ecommerce.multivendor.multivendor.repository.HomeCategoryRepository;
import com.ecommerce.multivendor.multivendor.service.HomeCatogeryService;
@Service
public class HomeCategoryServiceImpl implements HomeCatogeryService {
@Autowired
private HomeCategoryRepository homeCategoryRepository;
	@Override
	public HomeCategory createHomeCategory(HomeCategory homeCategory) {
		
		return homeCategoryRepository.save(homeCategory);
	}

	@Override
	public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
		if(homeCategoryRepository.findAll().isEmpty()) {
			return homeCategoryRepository.saveAll(homeCategories);
		}
		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
		HomeCategory existingCategory=homeCategoryRepository.findById(id).
				orElseThrow(()->new Exception("Category not found"));
		if(homeCategory.getImage()!=null) {
			existingCategory.setImage(homeCategory.getImage());
		}
		if(homeCategory.getCategoryId()!=null) {
			existingCategory.setCategoryId(homeCategory.getCategoryId());
		}
		return homeCategoryRepository.save(existingCategory);
	}

	@Override
	public List<HomeCategory> getAllCategories() {
		

		return homeCategoryRepository.findAll();
	}

}
