package com.baihoomuch.cloud.repository;

import com.baihoomuch.cloud.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/26
 *
 *  ProductCategory实体向上实现的dao层操作接口其继承的（SpringBoot框架支持提供）JpaRepository涉及到泛型传参有必要解释下
 *      JpaRepository<T, ID> 表映射的实体，实体映射表的主键id
 *
 *  其JpaRepository 简单说明一下相当于spring框架提供的hibernateTemplate ，留个印象
 */
public interface  ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     *springBoot 提供更具映射对象实体智能匹配查询的功能，遵循默认的规则
     * 以下查询为例勉强解释：
     *    通过类目编号集查找出映射对象数据集
     *    (findBy*) ------查找对象通过映射对象字段
     *    (*In) ------- 本身指的就是传参条件会是集合对象数据
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
