package com.baihoomuch.cloud.dto;

import com.baihoomuch.cloud.dataobject.OrderDetail;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 *
 * 在业务逻辑中OrderMaster类关联OrderDetail集合，OrderMaster类中若构造集合属性，不建议在映射类直接操作！
 * 通常我们称作数据传输对象（data Transfer Object）
 *
 */
//@Data//lombok.Data; 包含了一系列 getter，setter，toString 方法
//@Getter  //lombok.Getter ，只包含getter方法
//@Setter  //lombok.Setter ，只包含setter方法
public class OrderMasterDTO {
    /**
     * @param orderId 订单id
     * @param buyerName 买家名字
     * @param buyerPhone 买家电话
     * @param buyerAddress 买家地址
     * @param buyerOpenid 买家微信的Openid
     * @param orderAmount 订单金额
     * @param orderStatus 订单状态，默认为 0 新下单
     * @param payStatus 支付状态, 默认 0 未支付
     * @param createTime 创建时间
     * @param updateTime 更新时间
     */
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus ;
    private Integer payStatus ;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetails;
    
    
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getBuyerOpenid() {
		return buyerOpenid;
	}
	public void setBuyerOpenid(String buyerOpenid) {
		this.buyerOpenid = buyerOpenid;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
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
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

    
    
}
