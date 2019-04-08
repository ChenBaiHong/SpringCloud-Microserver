package com.baihoomuch.cloud.controller;


import com.baihoomuch.cloud.dataobject.ProductCategory;
import com.baihoomuch.cloud.dataobject.ProductInfo;
import com.baihoomuch.cloud.service.CategoryService;
import com.baihoomuch.cloud.service.ProductService;
import com.baihoomuch.cloud.utils.ResultVOUtil;
import com.baihoomuch.cloud.vo.ProductCategoryVO;
import com.baihoomuch.cloud.vo.ProductInfoVO;
import com.baihoomuch.cloud.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 * 买家商品控制层
 */
@RestController //注解启动加载, 表示该类控制返回的是json格式
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * http协议采用Get方式传值
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {
        //1. 查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2. 通过上架的商品类目编号查询出所有类目（一次性查询）
        /*
        List<Integer> categoryTypes = new ArrayList<Integer>();
        //传统方法
        for(ProductInfo productInfo: productInfoList) {
            categoryTypes.add(productInfo.getCategoryType());
        }
        */
        //java 8 新特性精简方法(lambda表达式)

        List<Integer> categoryTypes = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);
        //3. 数据拼装
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<ProductCategoryVO>();
        for (ProductCategory productCategory : productCategoryList) {
            //前端外层类目视图对象
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryType(productCategory.getCategoryType());
            productCategoryVO.setCategoryName(productCategory.getCategoryName());
            //类目视图对象包含商品视图对象
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //spring框架提供的对象拷贝
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productCategoryVO.setProductInfoVOList(productInfoVOList);
            productCategoryVOList.add(productCategoryVO);
        }
        return ResultVOUtil.success(productCategoryVOList);
    }
}

