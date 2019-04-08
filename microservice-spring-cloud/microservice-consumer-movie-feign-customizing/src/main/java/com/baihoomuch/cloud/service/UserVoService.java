package com.baihoomuch.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baihoomuch.cloud.microservice.UserFeignClient;
import com.baihoomuch.cloud.vo.UserVo;

/**
 * 
 * @author Administrator 服务层
 */
@Service
public class UserVoService {

	@Autowired
	private UserFeignClient userFeignClient;


	/**
	 * 获取用户视图对象通过对象id<br>
	 * 告知：
	 * 		feign 自己的微服务远程请求方式
	 * @param objectid
	 * @return
	 */
	public UserVo findUserVoById(Long objectid) {
		return userFeignClient.findUserById(objectid);
	}
}
