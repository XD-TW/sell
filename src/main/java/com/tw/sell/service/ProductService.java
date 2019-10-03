package com.tw.sell.service;

import com.tw.sell.dto.CartDTO;
import com.tw.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * 查询一个商品
     * @param productId 商品id
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有未下架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品(含下架商品)
     * @param pageable 分页参数
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 保存商品
     * @param info 商品对象
     * @return
     */
    ProductInfo save(ProductInfo info);

    /**
     * 查询指定类目的商品
     * @param type
     * @return
     */
    List<ProductInfo> findByCategoryType(Integer type);

    //加库存
    void increateStock(List<CartDTO> cartDTOS);

    //减库存
    void decreateStock(List<CartDTO> cartDTOS);

}
