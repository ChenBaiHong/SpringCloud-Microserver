package com.baihoomuch.cloud.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: microservice-simple-consumer-movie
 * auther Administrator on 2018/7/6
 */
public class UserVo {
    /**
     * @param username 用户名称
     * @param name 真实名称
     * @param age 年龄
     * @param balance  账目清单
     */
    @JsonProperty("用户名")
    private String username;
    @JsonProperty("真实名")
    private String name;
    @JsonProperty("年龄")
    private Integer age;
    @JsonProperty("账目清单")
    private BigDecimal balance;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "UserVo [username=" + username + ", name=" + name + ", age=" + age + ", balance=" + balance + "]";
	}
    
}
