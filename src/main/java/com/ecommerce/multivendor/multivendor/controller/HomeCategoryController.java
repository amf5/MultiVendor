package com.ecommerce.multivendor.multivendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Home;
import com.ecommerce.multivendor.multivendor.modal.HomeCategory;
import com.ecommerce.multivendor.multivendor.service.HomeCatogeryService;
import com.ecommerce.multivendor.multivendor.service.HomeService;

@RestController

public class HomeCategoryController {
	@Autowired
	private HomeCatogeryService homeCatogeryService;
	//@Autowired
	private HomeService homeService;
	@PostMapping("/home/categories")
	public ResponseEntity<Home> createCategories(@RequestBody List<HomeCategory>homeCategories){
		List<HomeCategory>homeCategories2=homeCatogeryService.createCategories(homeCategories);
		Home home=homeService.createHomePageData(homeCategories2);
		return new ResponseEntity<Home>(home,HttpStatus.ACCEPTED);
	}
	@GetMapping("/admin/home-category")
	public ResponseEntity<List<HomeCategory>> getHomeCategory(){
		
		List<HomeCategory> categories=homeCatogeryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}
	
	
	@PatchMapping("/admin/home-category/{id}")
	public ResponseEntity<HomeCategory> updateHomeCategory(@PathVariable Long id,@RequestBody HomeCategory homeCategory)
			throws Exception{
		
		HomeCategory updateCategory=homeCatogeryService.updateHomeCategory(homeCategory, id);
		return ResponseEntity.ok(updateCategory);

}
}