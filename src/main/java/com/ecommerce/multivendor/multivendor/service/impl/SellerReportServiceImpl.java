package com.ecommerce.multivendor.multivendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.SellerReport;
import com.ecommerce.multivendor.multivendor.repository.SellerReportRepository;
import com.ecommerce.multivendor.multivendor.service.SellerReportService;

@Service
public class SellerReportServiceImpl implements SellerReportService{
@Autowired
private SellerReportRepository sellerReportRepository;
	@Override
	public SellerReport getSellerReport(Seller seller) {
		SellerReport sellerReport=sellerReportRepository.findBySellerId(seller.getId());
		if(sellerReport==null) {
			SellerReport newReport=new SellerReport();
			newReport.setSeller(seller);
			return sellerReportRepository.save(newReport);
			
		}
		return sellerReport;
	}

	@Override
	public SellerReport updateSellerReport(SellerReport sellerReport) {
	
		return sellerReportRepository.save(sellerReport);
	}

}
