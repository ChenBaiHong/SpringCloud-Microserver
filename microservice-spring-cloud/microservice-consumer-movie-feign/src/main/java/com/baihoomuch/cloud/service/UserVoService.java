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

	/**
	 * feign get方式获取微服务
	 * 	get传对象方式。预期会出错的
	 * @param userVo
	 * @return
	 */
	public UserVo getTest(UserVo userVo) {
		return userFeignClient.getTest(userVo);
	}
	/**
	 * feign get方式获取微服务
	 * 	get传值方式
	 * @param age
	 * @param username
	 * @return
	 */
	public UserVo getTest2(Integer age, String username) {
		// TODO Auto-generated method stub
		return  userFeignClient.getTest2(age , username);
	}
	/**
	 * feign post方式获取微服务
	 * 	post传对象方式，正确
	 * @param userVo
	 * @return
	 */
	public UserVo postTest(UserVo userVo) {
		return userFeignClient.postTest(userVo);
	}
}
