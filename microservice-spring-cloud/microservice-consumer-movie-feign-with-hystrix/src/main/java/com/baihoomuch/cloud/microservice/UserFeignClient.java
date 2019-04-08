package com.baihoomuch.cloud.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.baihoomuch.cloud.microservice.config.FeignConfig;
import com.baihoomuch.cloud.vo.UserVo;

/**
 * 		调用microservice-provider-user工程项服务 该程序已经具备了Feign的功能，现在来实现一个简单的feign
 *		client。新建一个UserFeignClient的接口， 在接口上加@FeignClient注解来声明一个Feign
 *		Client。<br>
 *
 * 	spring 2.0.3 以上feign-hystrix 断路启用fallback 必须配置configuration<br>
 * 
 * 	fallback： 
 * 		远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式<br>
 * @author Administrator 组件层
 * 
 * 
 */
@Component
@FeignClient(value="microservice-provider-user", configuration=FeignConfig.class ,fallback=HystrixClientFallback.class)
public interface UserFeignClient {
	/**
	 * 微服务“提供端” 控制层方法，接口化
	 * 
	 * spring Cloud 2.0.3 支持的feign的两个坑，
	 * 	1. 早期spring Cloud 2.0.0以下版本，feign对 @GetMapping 不支持，
	 * 		正确写法@RequestMapping(value="/findUser/{objectid}" , method = RequestMapping.GET)
	 * 
	 * 	2. “@PathVariable”都设置value（值）
	 * 
	 * @param objectid
	 * @return
	 */
	@GetMapping("/msprovider/userContro/findUser/{objectid}") //自己注意填的坑： microservice-provider-user === 127.0.0.1:7900
	//@RequestMapping(value="/msprovider/userContro/findUser/{objectid}" , method=RequestMethod.GET)
	public UserVo findUser(@PathVariable("objectid") Long objectid); // 注意：这里和微服务“提供端” 控制层方法的区别
}
