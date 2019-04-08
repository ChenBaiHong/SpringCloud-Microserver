package com.baihoomuch.cloud.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 */
@Data
public class UserVo {
    /**
     * @param username 用户名称
     * @param name 真实名称
     * @param age 年龄
     * @param balance  账目清单
     */
	@JsonProperty("唯一ID")
    private Long  objectid;
    @JsonProperty("用户名")
    private String username;
    @JsonProperty("真实名")
    private String name;
    @JsonProperty("年龄")
    private Integer age;
    @JsonProperty("账目清单")
    private BigDecimal balance;
}
