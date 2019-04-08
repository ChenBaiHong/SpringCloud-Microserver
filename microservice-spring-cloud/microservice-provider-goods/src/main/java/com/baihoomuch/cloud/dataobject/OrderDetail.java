package com.baihoomuch.cloud.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 * 订单详情表
 */
@Entity
@DynamicUpdate //动态更新，比如对应数据库最后更新时间
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
//@Getter  //lombok.Getter ，只包含getter方法
//@Setter  //lombok.Setter ，只包含setter方法
public class OrderDetail {
    /**
     * @param detailId 订单详情id
     * @param orderId 订单id
     * @param productId 商品id
     * @param productName 商品名称
     * @param productPrice 商品价格
     * @param productQuantity 商品数量
     * @param productIcon 小图
     * @param createTime 创建时间
     * @param updateTime 更新时间
     */
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
    private Date createTime;
    private Date updateTime;
    
    
    
    
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
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
	public Integer getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getProductIcon() {
		return productIcon;
	}
	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
    
}
