package com.baihoomuch.cloud.repository;

import com.baihoomuch.cloud.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 *  ProductInfo实体向上实现的dao层操作接口其继承的（SpringBoot框架支持提供）JpaRepository涉及到泛型传参有必要解释下
 *      JpaRepository<T, ID> 表映射的实体，实体映射表的主键id
 *
 */
public interface ProductInfoRepository  extends JpaRepository<ProductInfo , String> {

    /**
     *springBoot 提供更具映射对象实体智能匹配的功能，遵循默认的规则
     * 以下查询为例勉强解释：
     *    通过商品状态查找出上架或下架映射对象数据集
     *    (findBy*) ------查找对象通过映射对象字段
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
