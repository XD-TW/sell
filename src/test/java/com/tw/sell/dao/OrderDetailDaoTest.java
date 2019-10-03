package com.tw.sell.dao;

import com.tw.sell.model.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    public void save(){

        OrderDetail od = new OrderDetail();
        od.setDetailId("1");
        od.setOrderId("2");
        od.setProductId("3");
        od.setProductName("冰爽柠檬茶");
        od.setProductPrice(new BigDecimal(11.9));
        od.setProductQuantity(10);
        od.setProductIcon("xx.png");
        OrderDetail save = dao.save(od);
        assert save != null;

    }


    @Test
    public void findByOrderIdTest(){
        List<OrderDetail> id = dao.findByOrderId("2");
        assert id.size() > 0;
        System.out.println(id);
    }
}