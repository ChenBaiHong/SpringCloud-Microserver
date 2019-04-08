package com.baihoomuch.cloud.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baihoomuch.cloud.microservice.config.FeignConfig;
import com.baihoomuch.cloud.vo.UserVo;

/**
 * 调用microservice-provider-user工程项服务 该程序已经具备了Feign的功能，现在来实现一个简单的feign
 * client。新建一个UserFeignClient的接口， 在接口上加@FeignClient注解来声明一个Feign
 * Client。value为远程调用其他服务的服务名，FeignConfig.class为配置类，
 * 在UserFeignClient内部有一个findUser()的方法，该方法通过Feign来调用microservice-provider-user服务的"/findUser/{objectid}"的API接口。
 * 
 * @author Administrator 组件层
 * 
 * 
 */
@Component
@FeignClient(value="microservice-provider-user" , configuration=FeignConfig.class) // 微服务“提供端”给spring的应用名称
public interface UserFeignClient {
	
	/**
	 * 微服务“提供端” 控制层方法，接口化
	 * 
	 * spring Cloud 2.0.3 支持的feign的两个坑，
	 * 	1. 早期spring Cloud 2.0.0以下版本，feign对 @GetMapping 不支持，正确写法@RequestMapping(value="/findUser/{objectid}" , method = RequestMapping.GET)
	 * 	2. @PathVariable 都设置value（值）
	 * @param objectid
	 * @return
	 */
	@GetMapping("/msprovider/userContro/findUser/{objectid}") //自己注意填的坑： microservice-provider-user === 127.0.0.1:7900
	public UserVo findUser(@PathVariable("objectid") Long objectid); // 注意：这里和微服务“提供端” 控制层方法的区别
	
	/**
	 * feign get方式获取微服务
	 * RequestBody获取微服务控制层调用方法返回的对象
	 * 
	 * 这里有一个坑，该请求式不会成功，只要参数式复杂对象，即使指定是get方法，feign依然会以post方式提交请求
	 * 如果一定多参数get方法发送请求，那么就只能如下方式
	 * 		 xxxMethod(@RequestParam("objectid") String objectid ,@RequestParam("username") String username  )
	 * 		否则前端页面会抛出：
	 * 			status 405 reading UserFeignClient#getTest(UserVo); content: {"timestamp":"2018-08-01T11:07:02.913+0000","status":405,
	 * 			"error":"Method Not Allowed","message":"Request method 'POST' not supported","path":"/msprovider/userContro/getTest"}
	 * @param userVo
	 * @return
	 */
	@GetMapping("/msprovider/userContro/getTest")
	 public UserVo getTest(@RequestBody UserVo userVo) ;
	
	/**
	 * 正确的get请求方式！
	 * @param age
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/msprovider/userContro/getTest2", method = RequestMethod.GET)
	 public UserVo getTest2(@RequestParam("age") Integer age ,@RequestParam("username") String username  );
	
	 
	 /**
	  * feign post方式获取微服务
	  * RequestBody获取微服务控制层调用方法返回的对象
	  * @param userVo
	  * @return
	  */
	 @PostMapping("/msprovider/userContro/postTest")
	//@RequestMapping(value = "/msprovider/userContro/postTest" , method = RequestMethod.POST)
	 public @ResponseBody UserVo postTest(@RequestBody UserVo userVo) ;
}
