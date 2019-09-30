package com.tw.sell.service.impl;

import com.tw.sell.dao.ProductCategoryDao;
import com.tw.sell.model.ProductCategory;
import com.tw.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao dao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return dao.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> category) {
        return dao.findByCategoryTypeIn(category);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
