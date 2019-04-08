package com.baihoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 简单写一个Eureka Server
 */
@SpringBootApplication
@EnableEurekaServer //Eureka 服务注册
public class MicroserviceDiscoveryEurekaHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDiscoveryEurekaHaApplication.class, args);
    }
}
