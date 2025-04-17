package com.ecommerce.multivendor.multivendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.multivendor.multivendor.modal.Deal;
import com.ecommerce.multivendor.multivendor.response.ApiResponse;
import com.ecommerce.multivendor.multivendor.service.DealService;

@RestController
@RequestMapping("/admin/deals")
public class DealController {
@Autowired
private DealService dealService;
@PostMapping
public ResponseEntity<Deal> creatDeals(@RequestBody Deal deal){
	Deal createdDeals=dealService.createDeal(deal);
	return new ResponseEntity<Deal>(createdDeals,HttpStatus.ACCEPTED);
}
@PatchMapping("/{id}")
public ResponseEntity<Deal> updateDeal(@PathVariable Long id,@RequestBody Deal deal)throws Exception{
	Deal updatedDeal=dealService.updateDeal(deal, id);
	return ResponseEntity.ok(updatedDeal);
}
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse> deleteDeals(@PathVariable Long id)throws Exception{
	dealService.deleteDeal(id);
	ApiResponse response=new ApiResponse();
	response.setMessage("DealDelete");
	return new ResponseEntity<ApiResponse>(response,HttpStatus.ACCEPTED);
}
}
