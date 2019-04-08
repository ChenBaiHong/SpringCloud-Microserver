package com.baihoomuch.cloud.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 *
 * http请求返回的最外层商品对象
 * 含有商品隐私数据不建议继承，从新建立对象，把需要展示的字段保持一致
 */
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
public class ProductInfoVO{
    /**
     * @param productId 商品信息ID
     * @param productName 商品名称
     * @param productPrice 商品价格
     * @param productDescription 商品描述
     * @param productIcon 商品小图
     */
    //@JsonProperty 设置该字段json传值显示的名称
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
    
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductIcon() {
		return productIcon;
	}
	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}
    
    
    
}
