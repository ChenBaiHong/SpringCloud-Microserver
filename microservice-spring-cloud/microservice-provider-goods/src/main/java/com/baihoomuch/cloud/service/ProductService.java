package com.baihoomuch.cloud.service;

import com.baihoomuch.cloud.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 * （ProductInfo）商品映射实体业务层操作逻辑
 */
public interface ProductService {
    /**
     * 通过商品id查找某一商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * 在架商品默认状态是0
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品要分页
     * @param pageable springBoot框架提供的分页接口类,具体实现类PageRequest
     * @return  返回的不是list对象而是spring提供的page对象
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 商品保存
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */

    /**
     * 减库存
     */

}
