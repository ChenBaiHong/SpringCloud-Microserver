package com.baihoomuch.cloud.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import com.baihoomuch.cloud.microservice.config.FeignConfig;
import com.baihoomuch.cloud.vo.UserVo;
import com.baihoomuch.config.FeignCustomizingConfiguration;

import feign.Param;
import feign.RequestLine;

/**
 * 调用microservice-provider-user工程项服务 该程序已经具备了Feign的功能，现在来实现一个简单的feign
 * client。新建一个UserFeignClient的接口， 在接口上加@FeignClient注解来声明一个Feign
 * Client。value为远程调用其他服务的服务名，MicroserviceConfiguration.class为配置类，
 * 在UserFeignClient内部有一个findUser()的方法，该方法通过Feign来调用microservice-provider-user服务的"/findUser/{objectid}"的API接口。
 * 
 * 	spring 2.0.3 以上feign-hystrix 断路启用fallback 必须配置configuration [FeignConfig.class]<br>
 * 
 * 	fallback： 
 * 		远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式<br>
 * 
 * @author Administrator 组件层
 * 
 * 
 */
@Component
@FeignClient(value="microservice-provider-user" , // 微服务“提供端”给spring的应用名称
	configuration= {FeignConfig.class,FeignCustomizingConfiguration.class},
	fallback=UserFeignClientFallback.class) 
public interface UserFeignClient {

	 /**
	  * feign 自己的微服务远程请求方式
	  * @param objectid
	  * @return
	  */
	 @RequestLine("GET /msprovider/userContro/findUser/{objectid}")
	 public UserVo findUserById(@Param("objectid") Long objectid); // 注意：这里和微服务“提供端” 控制层方法的区别
}
