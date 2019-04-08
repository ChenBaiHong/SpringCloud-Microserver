package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.baihoomuch.cloud.vo.UserVo;

/**
 * Description: microservice-simple-consumer-movie
 * auther Administrator on 2018/7/6
 */
@RestController
@RequestMapping("/contro")
public class controller {
    /**
     * RestTemplate 是访问远程微服务spring提供的模板对象，注意这里要自动注入restTemplate对象时必须在应用服务启动层new出该对象
     */
    @Autowired
    private RestTemplate restTemplate;
    
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
    	/**
    	 *  http://127.0.0.1:7900/msprovider/userContro/findUser/
    	 *  
    	 * VIP virtual IP 指的是虚拟IP地址
    	 * 
    	 * microservice-provider-user 工程提供者微服务----> application.yml 配置的中
    	 * 
    	 * spring: 
    	 * 	 application:
    	 *			name: microservice-provider-user
    	 */
        return restTemplate.getForObject("http://microservice-provider-user/msprovider/userContro/findUser/"+objectid,UserVo.class);
   
    }
    /**
     * ribbon 提供胡 LoadBalancerClient 获取虚拟IP信息
     * 
     * LoadBalancerClient 支持多线程，异步和反应模型
     * 
     * @return
     */
    @GetMapping("/test")
	public String testLoadBalanced() {
		// 输出结果是随机
		ServiceInstance microserviceProviderUser = this.loadBalancerClient.choose("microservice-provider-user");
		
		//输出结果是轮询
		ServiceInstance microserviceProviderUser2 = this.loadBalancerClient.choose("microservice-provider-user2");
		
		System.out.println(microserviceProviderUser.getServiceId() + ":" + microserviceProviderUser.getHost() + "->"
				+ microserviceProviderUser.getPort());
		System.out.println(microserviceProviderUser2.getServiceId() + ":" + microserviceProviderUser2.getHost() + "->"
				+ microserviceProviderUser2.getPort());
		return "baiHoo";
	}
}
