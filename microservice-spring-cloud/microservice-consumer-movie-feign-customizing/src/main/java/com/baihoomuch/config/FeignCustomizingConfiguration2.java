package com.baihoomuch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * 该配置类，解决 feign.FeignException: status 404 reading MicroserviceFeignClient#findServiceInfoFormEurekaByApplicationName(String) 异常<br>
 * 
 * 注意：该配置类一定要脱离程序的启动类（MicroserviceConsumerMovieFeignApplication）的控制范围。
 * 该抛出404异常，字面异常诠释：没有权限直接访问eureka服务端，feign需要账号密码方可访问
 * 
 * @author Administrator
 *
 */
@Configuration
public class FeignCustomizingConfiguration2 {
		
		/**
		 * eureka 请求权限拦截，设置账号，密码
		 * @return
		 */
		@Bean
		public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
			return  new BasicAuthRequestInterceptor("admin" , "admin");
		}
}
