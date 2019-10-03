package com.tw.sell.service.impl;

import com.tw.sell.model.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl impl;


    @Test
    @Transactional
    public void findOne() {
        ProductInfo one = impl.findOne("1");
        assert one != null;
        System.out.println(one);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = impl.findUpAll();
        assert upAll.size() > 0;
        for (ProductInfo info: upAll) {
            System.out.println(info);
        }
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> all = impl.findAll(request);
//        System.out.println(all.getTotalElements());
        assert all.getSize() > 0;
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("2");
        info.setProductName("大红袍寒天牛乳茶");
        info.setProductPrice(new BigDecimal(5));
        info.setProductStock(100);
        info.setProductDescription("这是一杯很好喝的牛乳茶");
        info.setProductIcon("http://xxxx.jpg");
        info.setProductStatus(0);
        info.setCategoryType(2);
        ProductInfo save = impl.save(info);
        assert null != save;
    }
}