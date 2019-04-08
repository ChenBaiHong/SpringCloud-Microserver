package com.baihoomuch.cloud.vo;

import lombok.Data;

/**
 * Description: http请求返回的最外层对象
 * auther Administrator on 2018/6/27
 */
//@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
public class ResultVO<T>{
    /**
     * @param code 错误码
     * @param msg 提示信息
     * @param data 具体内容
     */
    private Integer code;
    private String msg;
    private T data;
    
    
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
    
    
    
}
