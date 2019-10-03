package com.tw.sell.service.impl;

import com.tw.sell.dto.OrderDTO;
import com.tw.sell.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderimpl;

    public final String BUYER_OPENID = "110110";

    @Test
    public void create() {
        OrderDTO d = new OrderDTO();
        d.setBuyerAddress("慕课网");
        d.setBuyerName("阿珍");
        d.setBuyerOpenid(BUYER_OPENID);
        d.setBuyerPhone("12548545856");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("3");
        o1.setProductQuantity(9);
        orderDetailList.add(o1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(99);
        orderDetailList.add(o2);

        d.setDetailList(orderDetailList);
        d.setOrderAmount(new BigDecimal(50));


        OrderDTO dto = orderimpl.create(d);
        log.info("【创建订单】 result:" ,dto);
        assert dto != null;
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}