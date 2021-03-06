package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

import com.baihoomuch.cloud.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * Description: microservice-simple-consumer-movie auther Administrator on
 * 2018/7/6
 */
@RestController
@RequestMapping("/contro")
@SessionScope // 如果使用@sessioncope或@requestcope。如果遇到运行时异常，说明它找不到作用域上下文，则需要使用相同的线程
public class controller {
	/**
	 * RestTemplate 是访问远程微服务spring提供的模板对象，注意这里要自动注入restTemplate对象时必须在应用服务启动层new出该对象
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * RESTFul 格式例：http://127.0.0.1:7901/msconsumer/contro/findUser/1 HystrixCommand
	 * 如果微服务调用超时或出错，断路器走另一条分支方法，防止级联失败
	 * 
	 * @PathVariable 指的就是使用后面RESTFul传递的参数
	 * @param objectid
	 *            通过占位名称（占位名称得与形参名称一致）直接在访问的Url后面带参数值 例如：
	 * @return
	 */
	@GetMapping("/movie/{objectid}")
	@HystrixCommand(fallbackMethod = "findUserVoFallback", 
		/*
		 * 		如果希望某个线程本地上下文传播到@hystrix命令中，则默认声明不起作用，
		 * 因为它在线程池中执行命令(在超时的情况下)。您可以通过配置或直接在注释
		 * 中切换hystrix以使用与调用方相同的线程，方法是要求它使用不同的“隔离策略”
		 */
		commandProperties = @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"))
	public UserVo findUserVo(@PathVariable Long objectid) {
		return restTemplate.getForObject("http://microservice-provider-user/msprovider/userContro/findUser/" + objectid,
				UserVo.class);
	}

	/**
	 * 断路器分支回退调用方法，该方法必须和使用@HystrixCommand注解方法的参数一致
	 * 
	 * @param objectid
	 * @return
	 */
	public UserVo findUserVoFallback(Long objectid) {
		UserVo user = new UserVo();
		user.setObjectid(0L);
		user.setName("无名氏");
		user.setAge(0);
		user.setUsername("无名氏");
		return user;
	}
}
