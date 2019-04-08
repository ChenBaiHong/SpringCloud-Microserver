package com.baihoomuch.cloud.microservice;

import org.springframework.stereotype.Component;

/**
 * FeignClient 远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式
 * @author Administrator
 *
 */
@Component
public class MicroserviceFeignClientFallback implements MicroserviceFeignClient {
	/**
	 * 根据eureka 服务应用名称，获取该详情信息,若超时或出现错误，调用该方法
	 */
	@Override
	public String findServiceInfoFormEurekaByApplicationName(String applicationName) {
		
		return applicationName;
	}

}
