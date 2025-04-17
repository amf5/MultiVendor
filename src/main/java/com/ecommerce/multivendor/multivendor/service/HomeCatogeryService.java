package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import com.ecommerce.multivendor.multivendor.modal.HomeCategory;

public interface HomeCatogeryService {
HomeCategory createHomeCategory(HomeCategory homeCategory);
List<HomeCategory>createCategories(List<HomeCategory>homeCategories);
HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id) throws Exception;
List<HomeCategory>getAllCategories();
}
