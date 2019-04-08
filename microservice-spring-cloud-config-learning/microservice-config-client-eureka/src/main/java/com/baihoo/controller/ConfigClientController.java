package com.baihoo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Administrator
 *
 */
@RestController
public class ConfigClientController {
	
	/**
	 * @param profile 测试去远程仓库的装载值
	 */
	@Value("${profile}")
	private String profile;
	
	
	@GetMapping("/profile")
	public String getProfile() {
		return profile;
	}
}
