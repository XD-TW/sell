package com.tw.sell.service.impl;

import com.tw.sell.dao.OrderDetailDao;
import com.tw.sell.dao.OrderMasterDao;
import com.tw.sell.dto.CartDTO;
import com.tw.sell.dto.OrderDTO;
import com.tw.sell.enums.OrderStatusEnum;
import com.tw.sell.enums.PayStatusEnum;
import com.tw.sell.enums.ResultEnum;
import com.tw.sell.exception.SellException;
import com.tw.sell.model.OrderDetail;
import com.tw.sell.model.OrderMaster;
import com.tw.sell.model.ProductInfo;
import com.tw.sell.service.OrderService;
import com.tw.sell.service.ProductService;
import com.tw.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;

    private final OrderDetailDao detailDao;

    private final OrderMasterDao masterDao;

    @Autowired
    public OrderServiceImpl(ProductService productService, OrderDetailDao detailDao, OrderMasterDao masterDao) {
        this.productService = productService;
        this.detailDao = detailDao;
        this.masterDao = masterDao;
    }

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //1.查询商品
        String orderId = KeyUtil.genUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail d: orderDTO.getDetailList()) {
            ProductInfo one = productService.findOne(d.getProductId());
            if(one == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = new BigDecimal(d.getProductQuantity()).multiply(one.getProductPrice()).add(orderAmount);
            //订单详情入库
            d.setDetailId(KeyUtil.genUniqueKey());
            d.setOrderId(orderId);
            BeanUtils.copyProperties(one,d);
            detailDao.save(d);
        }

        //3.写入数据库(OrderMaster)和(OrderDetail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        masterDao.save(orderMaster);
        //4.扣掉库存
        List<CartDTO> cartDTOS = orderDTO.getDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreateStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
