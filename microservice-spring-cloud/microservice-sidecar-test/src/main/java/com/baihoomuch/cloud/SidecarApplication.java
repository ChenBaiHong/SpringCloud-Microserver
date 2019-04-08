package com.baihoomuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * 
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SidecarApplication {
    /**
     * 控制层需要RestTemplate远程访问微服务，服务启动层与控制层是相互关照的关系
     * LoadBalanced 已经整合ribbon负载均衡的算法,让 restTemplate 具有负载均衡的能力
     * @return
     */
    @LoadBalanced  // 由ribbon提供支持
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(SidecarApplication.class, args);
    }
}
