package com.ecommerce.multivendor.multivendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.multivendor.multivendor.modal.SellerReport;
@Repository
public interface SellerReportRepository  extends JpaRepository<SellerReport, Long>{
SellerReport findBySellerId(Long sellerId);
}
