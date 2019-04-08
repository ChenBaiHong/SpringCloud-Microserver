package com.baihoomuch.cloud.service;

import com.baihoomuch.cloud.dataobject.ProductCategory;

import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 *
 * （ProductCategory）类目映射实体业务层操作逻辑
 */
public interface CategoryService {
    /**
     * 通过ID查找类目
     * @param productCategoryId
     * @return
     */
    ProductCategory findOne(Integer productCategoryId);

    /**
     * 查询所有类目
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 通过类目编号集查找出映射对象数据集
     * @param categoryTypes
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    /**
     * 保存类目
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
