package com.wxy.lab.product.service;

import com.wxy.lab.product.common.DecreaseStockInput;
import com.wxy.lab.product.common.ProductInfoOutput;
import com.wxy.lab.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {

    //查询所有在架商品列表
    List<ProductInfo> findUpAll();

    //查询商品列表
    List<ProductInfoOutput> findList(List<String> productIdList);

    //扣库存
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);


}
