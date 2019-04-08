package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    /**
     * RESTFul 格式例：http://127.0.0.1:7901/msconsumer/contro/findUser/1
     * @PathVariable 指的就是使用后面RESTFul传递的参数
     * @param objectid 通过占位名称（占位名称得与形参名称一致）直接在访问的Url后面带参数值 例如：
     * @return
     */
    @GetMapping("/movie/{objectid}")
    public UserVo findUserVo(@PathVariable Long objectid){
    	
    	ServiceInstance microserviceProviderUser = this.loadBalancerClient.choose("microservice-provider-user");
    	System.out.println(microserviceProviderUser.getServiceId() + ":" + microserviceProviderUser.getHost() + "->"
				+ microserviceProviderUser.getPort());
    	
        return userVoService.findUserVo(objectid);
    }
    
    /**
     * 微服务，调用测试get方式传对象
     * @param userVo
     * @return
     */
    @GetMapping("/getTest")
	 public UserVo getTest(UserVo userVo) {
    	System.out.println(userVo.toString());
    	return userVoService.getTest( userVo);
    }
    /**
     * 微服务，调用测试get方式传参
     * @param userVo
     * @return
     */
    @GetMapping("/getTest2")
	 public UserVo getTest2(UserVo userVo) {
    	System.out.println(userVo.toString());
    	return userVoService.getTest2( userVo.getAge() , userVo.getUsername());
    }
    /**
     * 微服务，调用测试post方式传对象
     * @param userVo
     * @return
     */
	 @GetMapping("/postTest")
	 public UserVo postTest(UserVo userVo) {
		 System.out.println(userVo.toString());
		 return userVoService.postTest(userVo);
	 }
}

