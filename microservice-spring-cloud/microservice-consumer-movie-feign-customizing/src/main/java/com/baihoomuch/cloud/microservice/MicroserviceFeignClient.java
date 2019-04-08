package com.baihoomuch.cloud.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.baihoomuch.config.FeignCustomizingConfiguration2;

/**
 * 	    请求eureka服务端；如果url存在那么name的作用仅仅是命名名称
 * 	并不是远程微服务工程应用名，否则就是eureka application的远程工程应用名称
 * @author Administrator
 *
 */
@Component
@FeignClient(name="eureka-service" , url="http://localhost:8761/" , configuration=FeignCustomizingConfiguration2.class) 
public interface MicroserviceFeignClient {
	
	/**
	 * 根据eureka 服务应用名称，获取该详情信息
	 * @param applicationName
	 * @return
	 */
	@GetMapping("/eureka/apps/{applicationName}")
	public String findServiceInfoFormEurekaByApplicationName(@PathVariable("applicationName") String applicationName);	
}
