package com.ecommerce.multivendor.multivendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Deal;
import com.ecommerce.multivendor.multivendor.modal.HomeCategory;
import com.ecommerce.multivendor.multivendor.repository.DealRepository;
import com.ecommerce.multivendor.multivendor.repository.HomeCategoryRepository;
import com.ecommerce.multivendor.multivendor.service.DealService;

@Service
public class DealServiceImpl  implements DealService{
@Autowired
private DealRepository dealRepository;
@Autowired
private HomeCategoryRepository homeCategoryRepository;
	@Override
	public List<Deal> getDeals() {
	
		return dealRepository.findAll();
	}

	@Override
	public Deal createDeal(Deal deal) {
		HomeCategory homeCategory=homeCategoryRepository.findById(deal.getId()).orElse(null);
		Deal newDeal=dealRepository.save(deal);
		newDeal.setCategory(homeCategory);
		newDeal.setDiscount(deal.getDiscount());
		return dealRepository.save(newDeal) ;
	}

	@Override
	public Deal updateDeal(Deal deal,Long id) throws Exception {
		Deal existingDeal=dealRepository.findById(id).orElse(null);
		HomeCategory homeCategory=homeCategoryRepository.findById(deal.getId()).orElse(null);
		if(existingDeal!=null) {
		if(deal.getDiscount()!=null) {
			existingDeal.setDiscount(deal.getDiscount());
		}
		if(homeCategory!=null) {
			existingDeal.setCategory(homeCategory);
			
		}
		return dealRepository.save(existingDeal);}
		throw new Exception("Deal not found...");
	}

	@Override
	public void deleteDeal(Long id) throws Exception {
		Deal  deal=dealRepository.findById(id).orElseThrow(()->new Exception ("Deal not found..."));
		dealRepository.delete(deal);
		
	}

}
