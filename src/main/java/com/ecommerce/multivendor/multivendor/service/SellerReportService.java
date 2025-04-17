package com.ecommerce.multivendor.multivendor.service;

import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.modal.SellerReport;

public interface SellerReportService {
SellerReport getSellerReport(Seller seller);
SellerReport updateSellerReport(SellerReport sellerReport);
}
