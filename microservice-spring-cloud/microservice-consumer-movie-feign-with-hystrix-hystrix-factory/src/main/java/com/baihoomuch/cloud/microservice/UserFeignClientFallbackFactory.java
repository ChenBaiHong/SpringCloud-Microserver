package com.baihoomuch.cloud.microservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.baihoomuch.cloud.vo.UserVo;

import feign.hystrix.FallbackFactory;

/**
 * FeignClient 远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式<br>
 * 回调工厂模式<br>
 * @author Administrator
 *
 */
@Component
public class UserFeignClientFallbackFactory implements  FallbackFactory<UserFeignClient>{
	 private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);

	@Override
	public UserFeignClient create(Throwable cause) {
		//打印出异常信息
		UserFeignClientFallbackFactory.LOGGER.info("fallback; reason was: {}", cause.getMessage());
		return  new UserFeignClient() {
			/**
			 * 微服务“提供端” 控制层方法，若调用出错，启用备用的默认的值
			 */
			@Override
			public UserVo findUser(Long objectid) {
				UserVo user = new UserVo();
				user.setObjectid(objectid);
				user.setAge(0);
				user.setBalance(new BigDecimal(0L));
				user.setName("无名氏");
				user.setUsername("无名氏");
				return user;
			}
		} ;
	}

}
