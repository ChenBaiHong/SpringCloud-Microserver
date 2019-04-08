package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihoomuch.cloud.microservice.MicroserviceFeignClient;
import com.baihoomuch.cloud.service.UserVoService;
import com.baihoomuch.cloud.vo.UserVo;

/**
 * Description: microservice-simple-consumer-movie
 * auther Administrator on 2018/7/6
 */
@RestController
@RequestMapping("/contro")
public class controller {
	
	@Autowired
	UserVoService userVoService;
    @Autowired
    private LoadBalancerClient loadBalancerClient; // ribbon 提供的加载客户端负载均衡的类
    @Autowired
    private MicroserviceFeignClient microserviceFeignClient;
    
    /**
     * RESTFul 格式例：http://127.0.0.1:7901/msconsumer/contro/findUser/1
     * @PathVariable 指的就是使用后面RESTFul传递的参数
     * @param objectid 通过占位名称（占位名称得与形参名称一致）直接在访问的Url后面带参数值 例如：
     * @return
     */
    @GetMapping("/movie/{objectid}")
    public UserVo findUserVo(@PathVariable Long objectid){
    	
    	ServiceInstance microserviceProviderUser = this.loadBalancerClient.choose("microservice-provider-user");
    	
    	//控制台打印
    	System.out.println(microserviceProviderUser.getServiceId() + ":" + microserviceProviderUser.getHost() + "->"
				+ microserviceProviderUser.getPort());
    
        return userVoService.findUserVoById(objectid);//feign 自己的微服务远程请求方式
    }
    
    /**
     * 根据服务应用名称，从eureka服务端中获取该详情信息
     * @param applicationName
     * @return
     */
    @GetMapping("/{applicationName}")
    public String findServiceInfoFormEurekaByApplicationName(@PathVariable String applicationName){
        return microserviceFeignClient.findServiceInfoFormEurekaByApplicationName(applicationName);
    }

}

