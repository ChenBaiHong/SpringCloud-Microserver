package com.baihoomuch.cloud.service.impl;

import com.baihoomuch.cloud.dataobject.ProductCategory;
import com.baihoomuch.cloud.repository.ProductCategoryRepository;
import com.baihoomuch.cloud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 * （ProductCategory）类目映射实体业务层操作逻辑实现
 *
 */
@Service //注解启动加载
public class CategoryServiceImpl implements CategoryService {
    @Autowired //spring实现自动装载实例实体
    private ProductCategoryRepository repository;

    public ProductCategory findOne(Integer productCategoryId) {
        Optional<ProductCategory> optional = repository.findById(productCategoryId);
        return optional.get();
    }


    public List<ProductCategory> findAll() {
        return repository.findAll();
    }


    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return repository.findByCategoryTypeIn(categoryTypes);
    }


    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
