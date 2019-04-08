package com.baihoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Cloud Config 分布式系统外部化配置提供服务器端支持，引入Spring Cloud Config注解
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class MicroserviceConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConfigServerApplication.class, args);
    }
   
}
