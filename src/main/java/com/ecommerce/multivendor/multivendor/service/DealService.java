package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import com.ecommerce.multivendor.multivendor.modal.Deal;

public interface DealService  {
List<Deal>getDeals();
Deal createDeal(Deal deal );
Deal updateDeal(Deal deal,Long id) throws Exception;
void deleteDeal(Long id) throws Exception;
}
