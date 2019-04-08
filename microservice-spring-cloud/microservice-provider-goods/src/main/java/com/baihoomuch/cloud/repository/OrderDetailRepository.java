package com.baihoomuch.cloud.repository;

import com.baihoomuch.cloud.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 *  OrderDetail实体向上实现的dao层操作接口其继承的（SpringBoot框架支持提供）JpaRepository涉及到泛型传参有必要解释下
 *      JpaRepository<T, ID> 表映射的实体，实体映射表的主键id
 *
 *  其JpaRepository 简单说明一下相当于spring框架提供的hibernateTemplate ，留个印象
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     *springBoot 提供更具映射对象实体智能匹配查询的功能，遵循默认的规则
     * 以下查询为例勉强解释：
     *    通过订单id查询出订单详情
     *    (findBy*) ------查找对象通过映射对象字段
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
