package com.baihoo.cloud.microservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baihoo.cloud.criteria.MeCriteria;
import com.baihoo.cloud.domain.MyFile;
import com.baihoo.cloud.microservice.config.FeignConfig;
import com.baihoo.cloud.vo.CriteriaPage;
import com.baihoo.cloud.vo.ResultVO;
import com.baihoo.cloud.vo.page.PageFeign;

/**
 * 		调用microservice-provider-uploadfile工程项服务 该程序已经具备了Feign的功能，现在来实现一个简单的feign
 *		client。新建一个UserFeignClient的接口， 在接口上加@FeignClient注解来声明一个Feign
 *		Client。<br>
 *
 * 	spring 2.0.3 以上feign-hystrix 断路启用fallback 必须配置configuration<br>
 * 
 * 	fallback： 
 * 		远程微服务调用，若调用超时或调用出现异常，启动hystrix断路器模式<br>
 * @author Administrator 组件层
 * 
 * 
 */
@Component
@FeignClient(value="microservice-provider-uploadfile", configuration=FeignConfig.class ,fallback=HystrixClientFallback.class  )
public interface FileFeignClient {
	
	/**
	 * TOD 错误的复现！
	 * 		1、@RequestBody和@RequestParam 在一個方法这两种传参方式中不能共存
	 * 		2、返回接受的类型不能是接口和抽线类，并且类里面属性字段也不能有接口和抽线类。JDK集合类，定义类除外
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param meCriteria
	 * @return
	 */
	@PostMapping("/msquery")
	public Page<MyFile> msquery(@RequestParam("async") boolean async,
														@RequestParam("pageIndex") Integer pageIndex, 
														@RequestParam("pageSize") Integer pageSize ,  
														@RequestBody MeCriteria meCriteria);
	/**
	 *  TODO bingo！微服务访问成功
	 *  注意：@RequestBody和@RequestParam 在一個方法中这两种传参方式中不能共存，否则Fegin调用不到服务方
	 * @param async
	 * @param pageIndex
	 * @param pageSize
	 * @param meCriteria
	 * @return
	 */
	@PostMapping(value="/msquery2",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ResultVO<String> msquery2(
													/*@RequestBody  MeCriteria meCriteria ,*/
													@RequestParam("async") boolean async,
													@RequestParam("pageIndex") Integer pageIndex, 
													@RequestParam("pageSize") Integer pageSize);
	/**
	 * TODO bingo！微服务访问成功
	 * 注意：@RequestBody和@RequestParam 在一個方法中这两种传参方式中不能共存，否则Fegin调用不到服务方
	 * @param meCriteria
	 * @return
	 */
	@PostMapping(value = "/msquery3" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResultVO<PageFeign<MyFile>> msquery3(@RequestBody CriteriaPage criteriaPage);
	
	/**
	 * TODO bingo！微服务访问成功
	 * @param id
	 * @return
	 */
	@GetMapping("/myfiles/{id}")
	public ResultVO<MyFile> getMyFile(@PathVariable("id") String id) ;
	/**
	 * 
	 * TODO bingo！微服务访问成功
	 * 删除文件
	 * @param id
	 * @return
	 */
	@DeleteMapping("/msc/del/{id}")
	public ResultVO<String> deleteMyFile(@PathVariable("id") String id);
	/**
	 * 
	 * TODO bingo！微服务访问成功
	 *  通过Feign调用服务方接口上载文件
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/mscupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResultVO<String> handleMyFileUpload(@RequestPart("file") MultipartFile file);
}
