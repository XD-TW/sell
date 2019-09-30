package com.tw.sell.dao;

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
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    @Transactional
    public void findAll(){
        ProductCategory one = productCategoryDao.getOne(8);
        System.out.println(one.toString());

//        List<ProductCategory> productCategory = productCategoryDao.findAll();
//        for (ProductCategory p: productCategory) {
//            System.out.println(p);
//        }
//        System.out.println();
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory p = new ProductCategory("女生最爱",5);
        ProductCategory save = productCategoryDao.save(p);
        assert null!= save;
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4,5,6);
        List<ProductCategory> byCategoryTypeIn = productCategoryDao.findByCategoryTypeIn(list);
        assert 0 < byCategoryTypeIn.size();
    }
}