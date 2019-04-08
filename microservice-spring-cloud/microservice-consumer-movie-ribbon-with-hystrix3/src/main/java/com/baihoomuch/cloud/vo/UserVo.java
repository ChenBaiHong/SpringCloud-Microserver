package com.baihoomuch.cloud.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
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
