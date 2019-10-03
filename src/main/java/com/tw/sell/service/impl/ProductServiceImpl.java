package com.tw.sell.service.impl;

import com.tw.sell.dao.ProductInfoDao;
import com.tw.sell.dto.CartDTO;
import com.tw.sell.enums.ProductStatusEnum;
import com.tw.sell.enums.ResultEnum;
import com.tw.sell.exception.SellException;
import com.tw.sell.model.ProductInfo;
import com.tw.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductInfoDao infoDao;

    @Autowired
    public ProductServiceImpl(ProductInfoDao infoDao) {
        this.infoDao = infoDao;
    }

    @Override
    public ProductInfo findOne(String productId) {
        return infoDao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return infoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return infoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo info) {
        return infoDao.save(info);
    }

    @Override
    public List<ProductInfo> findByCategoryType(Integer type) {
        return infoDao.findByCategoryType(type);
    }

    @Override
    public void increateStock(List<CartDTO> cartDTOS) {

    }

    @Override
    @Transactional
    public void decreateStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO: cartDTOS) {
            ProductInfo one = findOne(cartDTO.getProductId());
            if(one == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result =  one.getProductStock()-cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            one.setProductStock(result);
            infoDao.save(one);
        }
    }
}
