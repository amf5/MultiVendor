package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.domain.HomeCategorySection;
import com.ecommerce.multivendor.multivendor.modal.Deal;
import com.ecommerce.multivendor.multivendor.modal.Home;
import com.ecommerce.multivendor.multivendor.modal.HomeCategory;
import com.ecommerce.multivendor.multivendor.repository.DealRepository;
import com.ecommerce.multivendor.multivendor.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
@Autowired
private DealRepository dealRepository;
	@Override
	public Home createHomePageData(List<HomeCategory> allCategories) {
		List<HomeCategory> gridCategories=allCategories.stream().filter(category-> 
		category.getSection()==HomeCategorySection.GRID).collect(Collectors.toList());
		List<HomeCategory> shopByCategories=allCategories.stream().filter(category->
		category.getSection()==HomeCategorySection.SHOP_BY_CATEGORIES).collect(Collectors.toList());
		List<HomeCategory> electricCategories=allCategories.stream().filter(category->
		category.getSection()==HomeCategorySection.ELECTRIV_CATEGORIES).collect(Collectors.toList());
		List<HomeCategory> dealCategories=allCategories.stream().filter(category->
		category.getSection()==HomeCategorySection.DEALS).collect(Collectors.toList());
List<Deal>createdDeals=new ArrayList<>();
if(dealRepository.findAll().isEmpty()) {
	List<Deal> deals=allCategories.stream().filter(category->
	category.getSection()==HomeCategorySection.DEALS).map(category-> new Deal(null,10,category)).collect(Collectors.toList());
	createdDeals=dealRepository.saveAll(deals);
	

}else {
	createdDeals=dealRepository.findAll();
}
Home home=new Home();
home.setGrid(gridCategories);
home.setShopByCategories(shopByCategories);
home.setDeals(createdDeals);
home.setElectricCategories(electricCategories);
home.setDealCategories(dealCategories);

		
		return home;
	}

}
