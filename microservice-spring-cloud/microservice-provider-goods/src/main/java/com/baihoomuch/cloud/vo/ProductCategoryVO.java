package com.baihoomuch.cloud.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: sell
 * auther Administrator on 2018/6/27
 *
 * http请求返回的最外层类目对象
 * 含有类目隐私数据不建议继承，从新建立对象，把需要展示的字段保持一致
 */
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
public class ProductCategoryVO {
    /**
     * @param categoryName 类目名称
     * @param categoryType 类目编号
     * @param  productInfoVOList 商品集
     */

    //@JsonProperty 设置该字段json传值显示的名称
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
    
    
    
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}
	public List<ProductInfoVO> getProductInfoVOList() {
		return productInfoVOList;
	}
	public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
		this.productInfoVOList = productInfoVOList;
	}
    
    
    
    
}
