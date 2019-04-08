package com.baihoomuch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

/**
 * 该配置类，属于启用feign的默认的http注解协议<br>
 * 
 * 注意：该配置类一定要脱离程序的启动类（MicroserviceConsumerMovieFeignApplication）的控制范围，
 * 否则该启动类控制范围所有的请求方式均采用feign默认的http注解协议
 * 
 * @author Administrator
 *
 */
@Configuration
public class FeignCustomizingConfiguration {
	
		/**
		 * 1. feign 支持springmvc http注解协议
		 * 2. feign 以下配置告知使用自己的http注解协议
		 * @return
		 */
		@Bean
		public Contract feignContract() {
			return new feign.Contract.Default();
		}
		/**
		 * 配置feign的日志级别
		 * @return
		 */
		@Bean
		public Logger.Level feignLoggerLevel(){
			return Logger.Level.FULL;
		}
}
