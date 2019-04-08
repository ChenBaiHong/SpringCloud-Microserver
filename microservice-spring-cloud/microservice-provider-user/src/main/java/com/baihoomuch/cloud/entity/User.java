package com.baihoomuch.cloud.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 *
 * 实体名称和字段与映射的表名称和表字段不分大小写是一致的
 *
 */
@Data
@Entity
public class User {
    /**
     * @param objectid 唯一识别ID , 并自动增长
     * @param username 用户名称
     * @Param password 用户密码
     * @param name 真实名称
     * @param age 年龄
     * @param balance  账目清单
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  objectid;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;
    
}
