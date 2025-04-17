package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.domain.AccountStatus;
import com.ecommerce.multivendor.multivendor.modal.Seller;
import com.ecommerce.multivendor.multivendor.service.SellerService;

@RestController
@RequestMapping("/api")
public class AdminController {
@Autowired
private SellerService sellerService;
@PatchMapping("/seller/{id}/status/{status}")
public ResponseEntity<Seller> updateSellerStatus(@PathVariable Long id,
		@PathVariable AccountStatus status)throws Exception
{
	Seller updateSeller=sellerService.updateSellerAccountStatus(id, status);
	return ResponseEntity.ok(updateSeller);
	}

}
