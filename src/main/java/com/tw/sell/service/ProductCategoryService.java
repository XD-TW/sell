package com.tw.sell.service;

import com.tw.sell.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> category);

    ProductCategory save(ProductCategory productCategory);
}
