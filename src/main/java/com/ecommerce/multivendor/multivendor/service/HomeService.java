package com.ecommerce.multivendor.multivendor.service;

import java.util.List;

import com.ecommerce.multivendor.multivendor.modal.Home;
import com.ecommerce.multivendor.multivendor.modal.HomeCategory;

public interface HomeService {
public Home createHomePageData(List<HomeCategory>allCategories);
}
