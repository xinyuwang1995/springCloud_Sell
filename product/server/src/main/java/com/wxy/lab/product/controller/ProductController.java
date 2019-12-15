package com.wxy.lab.product.controller;

import com.wxy.lab.product.common.DecreaseStockInput;
import com.wxy.lab.product.common.ProductInfoOutput;
import com.wxy.lab.product.VO.ProductInfoVO;
import com.wxy.lab.product.VO.ProductVO;
import com.wxy.lab.product.VO.ResultVO;
import com.wxy.lab.product.dataobject.ProductCategory;
import com.wxy.lab.product.dataobject.ProductInfo;
import com.wxy.lab.product.service.CategoryService;
import com.wxy.lab.product.service.ProductService;
import com.wxy.lab.product.utils.ResultVOutil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //1.查询所有在架的商品
    //2.获取类目type类型表
    //3.查询类目
    //4.构造数据


    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        //1.查询所有在架的商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //2.获取类目type类型表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3.查询类目
        List<ProductCategory> categoryList =categoryService.findByCategoryTypeIn(categoryTypeList);

        //4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:categoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);


        }

        return ResultVOutil.success(productVOList);



    }

    //获取商品列表（订单）
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){

        return productService.findList(productIdList);


    }
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        productService.decreaseStock(decreaseStockInputList);

    }
}
