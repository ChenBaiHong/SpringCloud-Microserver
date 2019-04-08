package com.baihoomuch.cloud.service;

import com.baihoomuch.cloud.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 * （OrderMaster）订单映射实体业务层操作逻辑
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     *查询单个订单
     * @param OrderId
     * @return
     */
    OrderMasterDTO findOne(String OrderId);

    /**
     *查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     *取消订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO cancle(OrderMasterDTO orderMasterDTO);

    /**
     *完结订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    /**
     *支付订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO payid(OrderMasterDTO orderMasterDTO);
}
