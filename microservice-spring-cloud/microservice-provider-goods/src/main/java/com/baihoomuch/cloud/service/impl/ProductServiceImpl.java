package com.baihoomuch.cloud.service.impl;

import com.baihoomuch.cloud.dataobject.ProductCategory;
import com.baihoomuch.cloud.dataobject.ProductInfo;
import com.baihoomuch.cloud.enums.ProductStatusEnum;
import com.baihoomuch.cloud.repository.ProductInfoRepository;
import com.baihoomuch.cloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 * （ProductInfo）商品映射实体业务层操作逻辑实现
 *
 */
@Service //注解启动加载
public class ProductServiceImpl  implements ProductService {

    @Autowired //spring实现自动装载实例实体
    private ProductInfoRepository repository;

    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> optional = repository.findById(productId);
        return optional.get();
    }
    /**
     * 查询所有在架商品列表
     * @return
     */
    public List<ProductInfo> findUpAll() {

        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
    public Page<ProductInfo> findAll(Pageable pageable) {
        /**
         * 返回的不是list对象而是spring提供的page对象
         */
        return repository.findAll(pageable);
    }
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
