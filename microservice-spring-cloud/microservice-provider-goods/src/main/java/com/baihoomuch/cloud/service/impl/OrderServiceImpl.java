package com.baihoomuch.cloud.service.impl;

import com.baihoomuch.cloud.dto.OrderMasterDTO;
import com.baihoomuch.cloud.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 * （OrderMaster）订单映射实体业务层操作逻辑实现
 */
@Service //注解启动加载
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        //1. 查询商品（数量，价格）
        //2. 计算总价
        //3. 写入订单数据库（OrderMaster 和 OrderDetail）
        //4. 扣库存
        return null;
    }

    @Override
    public OrderMasterDTO findOne(String OrderId) {
        return null;
    }

    @Override
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDTO cancle(OrderMasterDTO orderMasterDTO) {
        return null;
    }

    @Override
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        return null;
    }

    @Override
    public OrderMasterDTO payid(OrderMasterDTO orderMasterDTO) {
        return null;
    }
}
