package com.baihoomuch.cloud.enums;

import lombok.Getter;

/**
 * Description: sell
 * auther Administrator on 2018/6/30
 * 订单状态，默认为新下单
 */
//@Getter //lombok.*; 包含了一系列 getter，setter，toString 方法
public enum OrderStatusEnum {
    NEW(0,"新下单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	} 
    
    
    
}
