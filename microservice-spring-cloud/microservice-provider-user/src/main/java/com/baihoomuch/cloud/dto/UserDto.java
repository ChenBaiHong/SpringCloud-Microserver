package com.baihoomuch.cloud.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 */
@Data
public class UserDto {
        /**
         * @param objectid 唯一识别ID , 并自动增长
         * @param username 用户名称
         * @Param password 用户密码
         * @param name 真实名称
         * @param age 年龄
         * @param balance  账目清单
         */

        private Long  objectid;
        private String username;
        private String password;
        private String name;
        private Integer age;
        private BigDecimal balance;
}
