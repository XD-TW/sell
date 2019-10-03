package com.tw.sell.dao;

import com.tw.sell.model.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao infoDao;

    @Test
    public void saveTest(){
        ProductInfo info = new ProductInfo();
        info.setProductId("1");
        info.setProductName("柠檬绿茶");
        info.setProductPrice(new BigDecimal(5));
        info.setProductStock(100);
        info.setProductDescription("这是一杯很好喝的柠檬绿茶");
        info.setProductIcon("http://xxxx.xxxx.com");
        info.setProductStatus(0);
        info.setCategoryType(2);
        ProductInfo save = infoDao.save(info);
        assert null != save;

    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> status = infoDao.findByProductStatus(0);
        assert null != status;
        System.out.println(status);
    }

    @Test
    public void findByCategoryTypeTest(){
        List<ProductInfo> type = infoDao.findByCategoryType(2);
        System.out.println(type);
    }
}