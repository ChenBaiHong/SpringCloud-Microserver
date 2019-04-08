package com.baihoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 简单写一个Eureka Server
 */
@SpringBootApplication
@EnableEurekaServer //Eureka 服务注册
public class MicroserviceDiscoveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDiscoveryEurekaApplication.class, args);
    }
    /**
     *  springsecurity 默认安全机制CSRF保护，默认是开启，因此无法解决eureka客户端登陆注册到eureka服务端！
     *  设置禁用，否则eureka客户端无法注册到该eureka服务端
     */
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
}
