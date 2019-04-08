package com.baihoomuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * 
 * @author Administrator
 *	在程序的启动类加上注解@EnableFeignClients开启Feign Client功能
 */
@SpringBootApplication
@EnableEurekaClient //声明式Eureka客户端，Eureka 服务端好集群管理
@EnableFeignClients // 声明式feign客户端，【替代RestTemplate，实现微服务调用】
public class MicroserviceConsumerMovieFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerMovieFeignApplication.class, args);
    }
}
