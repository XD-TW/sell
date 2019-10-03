package com.tw.sell.dao;

import com.tw.sell.model.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {


    @Autowired
    private OrderMasterDao dao;


    @Test
    public void sa0ve(){
        OrderMaster om = new OrderMaster();
        om.setOrderId("2");
        om.setBuyerName("阿强");
        om.setBuyerPhone("15485478565");
        om.setBuyerAddress("慕课网");
        om.setBuyerOpenid("110101");
        om.setOrderAmount(new BigDecimal(50.6));
        OrderMaster save = dao.save(om);
        assert save != null;
    }

    @Test
    public void findByBuyerOpenidTest(){
        PageRequest r = new PageRequest(0,2);
        Page<OrderMaster> openid = dao.findByBuyerOpenid("110101", r);
        System.out.println("openid.size:" + openid.getSize());

        assert 0 < openid.getSize();
        System.out.println(openid.getTotalElements());
    }
}