package com.wxy.lab.product.service.impl;

import com.wxy.lab.product.common.DecreaseStockInput;
import com.wxy.lab.product.common.ProductInfoOutput;
import com.wxy.lab.product.dataobject.ProductInfo;
import com.wxy.lab.product.enums.ProductStatusEnum;
import com.wxy.lab.product.enums.ResultEnum;
import com.wxy.lab.product.exception.ProductException;
import com.wxy.lab.product.repository.ProductInfoRepository;
import com.wxy.lab.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());

    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e ->{
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return output;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput:decreaseStockInputList){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer result =productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if(result < 0){
                throw new ProductException((ResultEnum.PRODUCT_STOCK_ERROR));
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }
}
