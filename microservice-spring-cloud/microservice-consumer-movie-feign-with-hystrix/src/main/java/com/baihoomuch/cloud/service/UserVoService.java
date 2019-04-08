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
	 * 获取用户视图对象
	 * 
	 * @param objectid
	 * @return
	 */
	public UserVo findUserVo(Long objectid) {
		return userFeignClient.findUser(objectid);
	}
}
