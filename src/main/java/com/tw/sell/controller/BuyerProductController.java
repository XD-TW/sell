package com.tw.sell.controller;

import com.tw.sell.model.ProductCategory;
import com.tw.sell.model.ProductInfo;
import com.tw.sell.service.impl.CategoryServiceImpl;
import com.tw.sell.service.impl.ProductServiceImpl;
import com.tw.sell.utils.ResultVOUtil;
import com.tw.sell.vo.ProductInfoVO;
import com.tw.sell.vo.ProductVO;
import com.tw.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductServiceImpl infoService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        ResultVO vo = new ResultVO();
        List<ProductVO> pvoList = new ArrayList<>();

        //首先查询所有上架商品
        List<ProductInfo> upAll = infoService.findUpAll();
        //获取所有商品的所有type并放入集合
        List<Integer> typeList = upAll.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(typeList);

        for (ProductCategory p: categoryList) {
            ProductVO pv = new ProductVO();
            pv.setCategoryName(p.getCategoryName());
            pv.setCategoryType(p.getCategoryType());
            List<ProductInfoVO> infoVOS = new ArrayList<>();
            for (ProductInfo info: upAll) {
                if(info.getCategoryType().equals(pv.getCategoryType())){
                    ProductInfoVO infovo = new ProductInfoVO();
                    BeanUtils.copyProperties(info,infovo);
                    infoVOS.add(infovo);
                }
            }
            pv.setData(infoVOS);
            pvoList.add(pv);
        }
//        List<ProductInfoVO> infoVO = new ArrayList<>();
//        List<ProductInfo> infolist;
//        //首先，查询所有类目
//        List<ProductCategory> categoryAll = categoryService.findAll();
//        for (ProductCategory productCategory1 : categoryAll) {
//            ProductVO pvo = new ProductVO();
//            pvo.setCategoryName(productCategory1.getCategoryName());
//            pvo.setCategoryType(productCategory1.getCategoryType());
//            for (int j = 0; j < categoryAll.size(); j++) {
//                infolist = infoService.findByCategoryType(categoryAll.get(j).getCategoryType());
//                if(infolist.size() > 0){
//                    if (productCategory1.getCategoryType() == infolist.get(j).getCategoryType()) {
//                        for (ProductInfo v: infolist) {
//                            ProductInfoVO ivo = new ProductInfoVO();
//                            ivo.setProductId(v.getProductId());
//                            ivo.setProductName(v.getProductName());
//                            ivo.setProductPrice(v.getProductPrice());
//                            ivo.setProductDescription(v.getProductDescription());
//                            ivo.setProductIcon(v.getProductIcon());
//                            infoVO.add(ivo);
//                        }
//                        pvo.setData(infoVO);
//                    }
//                }
//            }
//            pvolist.add(pvo);
//        }
        return ResultVOUtil.success(pvoList);
    }
}
