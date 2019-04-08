package com.baihoomuch.cloud.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihoomuch.cloud.service.UserService;
import com.baihoomuch.cloud.vo.UserVo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

/**
 * Description: 
 * 		TODO User 操作控制 ，该工程应用名称 microservice-simple-provider-user
 * 
 * auther 
 * 		Administrator on 2018/7/5
 * 
 */
@RestController //该注解表示response响应给客户端的对象都是json格式
@RequestMapping("/userContro")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 
     * @param objectid		通过占位名称（占位名称得与形参名称一致）直接在访问的Url后面带参数值 
     * @return
     */
    @GetMapping("/findUser/{objectid}")
    public UserVo findUser(@PathVariable Long objectid){
        UserVo userVo= new UserVo();
        BeanUtils.copyProperties(userService.findByObjectid(objectid),userVo);
         return  userVo;
    }
    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * SpringCloud 通过Eureka服务端的逻辑标识符（注册到Eureka的应用名称），支持 Feign (一个REST客户端构造器) 和 Spring 提供的 RestTemplate 去访问虚拟的地址而不是物理URL(http://ip:port)。
     * 也可以用物理服务器的固定列表配置Ribbon，可以将<Client>.ribbon.listOfServers设置为以逗号分隔的物理地址(或主机名)列表，其中<Client>是客户机的ID（microservice-consumer-movie-ribbon-without-eureka 的yml文件中可做参考）。
     * 也可以使用org.springframework.cloud.client.discovery.DiscoveryClient，它为发现客户端提供了一个简单的API(不特定于Netflix)，如下面的示例所示。
     * @return
     */
    @GetMapping("/serviceUrl")
    public URI serviceUrlByDiscovery() {
        List<ServiceInstance> list = discoveryClient.getInstances("MICROSERVICE-PROVIDER-USER");
        if (list != null && list.size() > 0 ) {
            return list.get(0).getUri();
        }
        return null;
    }
    @Autowired
    private EurekaClient eurekaClient;
    /**
     *	 拥有一个作为发现客户端的应用程序，就可以使用它从Eureka Server中发现服务实例。
     *  这一种方法是使用本机com.netflix.discovery.EurekaClient（而不是Spring Cloud DiscoveryClient），如以下示例所示：
     * @return
     */
    @GetMapping("/serviceUrlByEureka")
    public String serviceUrlByEurekaClient() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
        return instance.getHomePageUrl();
    }
    
    /**
     * 微服务，get请求方法传对象的方式
     * @param userVo
     * @return
     */
    @GetMapping("/getTest") //“提供者微服务”远程get方式测试，测试源---microservice-consumer-movie-feign
    public UserVo getTest(@RequestBody UserVo userVo) {
    	System.out.println(userVo);
		return userVo;
    }
    /**
     * 微服务，get请求方法传参的方式
     * @param age
     * @param username
     * @return
     */
    @GetMapping("/getTest2") //“提供者微服务”远程get方式测试，测试源---microservice-consumer-movie-feign
    private UserVo getTest2(Integer  age, String username) {
    	UserVo user = new UserVo();
    	user.setAge(age);
    	user.setUsername(username);
    	System.out.println(user);
		return user;
    }
    /**
     *  微服务，post请求方法传对象的方式
     * @param userVo
     * @return
     */
    @PostMapping("/postTest") //“提供者微服务”远程post方式测试，测试源---microservice-consumer-movie-feign
    public  UserVo postTest(@RequestBody UserVo userVo) {
    		System.out.println(userVo);
    		return userVo;
    }
}
