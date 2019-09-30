package com.tw.sell.service.impl;

import com.tw.sell.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl impl;

    @Test
    @Transactional
    public void findOne() {
        ProductCategory one = impl.findOne(8);
        assert null!= one;
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = impl.findAll();
        assert 0 < all.size();
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> in = impl.findByCategoryTypeIn(list);
        assert 0 < in.size();
    }

    @Test
    @Transactional
    public void save() {
        ProductCategory p = new ProductCategory();
        p.setCategoryName("奶茶系列");
        p.setCategoryType(8);
        impl.save(p);

    }
}