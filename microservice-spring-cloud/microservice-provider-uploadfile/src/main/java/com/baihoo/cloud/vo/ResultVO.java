package com.baihoo.cloud.vo;

import lombok.Data;

/**
 * Description: http请求返回的最外层对象
 * auther Administrator on 2018/6/27
 */
@Data //lombok.Data; 包含了一系列 getter，setter，toString 方法
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)   //过时的注解，诠释：数据传输对象传值页面前端，空值(Null)不做显示，页面不做显示
//@JsonInclude(JsonInclude.Include.NON_NULL) // 诠释：数据传输对象传值页面前端，空值(Null)不传入，页面不做显示
public class ResultVO<T>{
    /**
     * @param code 错误码
     * @param msg 提示信息
     * @param data 具体内容
     */
    private Integer code = -1;
    private String msg = "undefined";
    private T data;
}
