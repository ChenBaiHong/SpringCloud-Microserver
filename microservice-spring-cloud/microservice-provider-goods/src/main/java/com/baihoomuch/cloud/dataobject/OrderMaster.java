package com.baihoomuch.cloud.dataobject;


import com.baihoomuch.cloud.enums.OrderStatusEnum;
import com.baihoomuch.cloud.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 *
 * 订单信息
 */
@Entity
@DynamicUpdate //动态更新，比如对应数据库最后更新时间
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
//@Getter  //lombok.Getter ，只包含getter方法
//@Setter  //lombok.Setter ，只包含setter方法
public class OrderMaster {
    /**
     * @param orderId 订单id
     * @param buyerName 买家名字
     * @param buyerPhone 买家电话
     * @param buyerAddress 买家地址
     * @param buyerOpenid 买家微信的Openid
     * @param orderAmount 订单金额
     * @param orderStatus 订单状态，默认为新下单
     * @param payStatus 支付状态, 默认未支付
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;
    
    
    
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
    
    
    
}
