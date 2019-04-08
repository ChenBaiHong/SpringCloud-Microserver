package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 注意：RestTemplate类方法访问http://applicationName ，需要在JVM启动类中，在该RestTemplate配置类加入由ribbon提供 @LoadBalanced 注解
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/sidecarTest")
public class controller {
    /**
     * RestTemplate 是访问远程微服务spring提供的模板对象，注意这里要自动注入restTemplate对象时必须在应用服务启动层new出该对象
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * zuul 代理地址：
     * 		http://localhost:8040/microservice-sidecar 
     * @return
     */
    @GetMapping("/msc-node")
    public String mscNode(){
    	
        return restTemplate.getForObject("http://microservice-sidecar/" , String.class);
    }
}
