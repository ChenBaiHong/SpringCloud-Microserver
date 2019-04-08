
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

 当前工程为用户消费者者微服务 （microservice-consumer-movie-ribbon-with-hystrix）,整合ribbon负载均衡
 
> Eureka Client 是一个Java客户端，用于简化与 Eureka Server 的交互，客户端同时也具备一个内置的、使用轮询（round-robin）
  负载算法的负载均衡器。
  
## 四、自定义ribbon 负载均衡
1、ribbon负载均衡配置注意事项

> CustomConfiguration CLA 必须是一个 @Configuration 类，但是要注意，对于主应用程序上下文来说，它不是在@ComponentScan中。
否则，所有@RibbonClients都会共享它。如果使用@ComponentScan(或@SpringBootApplication)，则需要采取步骤避免包含它(例如，
您可以将其放在单独的、不重叠的包中，或者指定要在@ComponentScan中显式扫描的包)。
	
2、当前工程控制层 controller.java 通过RestTemplate，调用“提供端微服务【多个服务提供者】”实现负载均衡调用，负载规则通过
Java类书写配置

3、测试说明：微服务消费端 可根据定义的负载算法调用 多个微服务提供端的任意节点
  + MICROSERVICE-PROVIDER-USER [微服务ApplicationName应用名]	
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7901 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user[微服务ApplicationName应用名] : 7902 [端口]
			 
  + MICROSERVICE-PROVIDER-USER2 [微服务ApplicationName应用名]	
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user2 [微服务ApplicationName应用名] : 7903 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user2 [微服务ApplicationName应用名] : 7904 [端口]	

## 五、 整合ribbon负载均衡加入hystrix断路器模式
> Hystrix是Netflix开源的一款容错框架，包含常用的容错方法：线程池隔离、信号量隔离、熔断、降级回退。在高并发访问下

> 断路器模式就像是那些容易导致错误的操作的一种代理。这种代理能够记录最近调用发生错误的次数，然后决定使用允许操作继续，
或者立即返回错误。
	
* 1、健康指标 SpringBoot 2.0 + 以上配置
    ```
    #springboot2.0. 以上的配置项为：
    #actuator端口
    management:
      server:
        port: 9002
      endpoints:
        web:
       #   base-path: / #修改访问路径  2.0之前默认是/   2.0默认是 /actuator  可以通过这个属性值修改
          exposure:
            include: '*'  # 开放所有页面节点  默认只开启了health、info两个节点
    ```
* 2、连接的断路器的状态也在调用应用程序的上下文路径 /health 中公开，如下面的示例所示：
    + 网页页签，输入 http://localhost:9002/actuator/health，网页显示内容如下：
        ```
            ------------------------------------------------------------
            {
            status: "UP"
            }
            ------------------------------------------------------------
        ```
				
* 3、Hystrix Metrics Stream
    + 要启用Hystrix Metrics Stream，引入spring-boot-starter-actuator依赖
    + 并设置 management.endpoints.web.exsure.include：hystrix.stream。
	+ 这样做可以将/驱动器/hystrix.stream公开为管理端点
	+ 网页页签，输入 http://localhost:7916/msmovie/contro/movie/1，网页显示内容如下：
        ```
            ------------------------------------------------------------
            {
            唯一ID: 1,
            用户名: "user1",
            真实名: "baiHoo1",
            年龄: 20,
            账目清单: 100
            }
            ------------------------------------------------------------
        ```
	+ 网页页签，输入 http://localhost:9002/actuator/hystrix.stream，网页显示内容如下：
        ```
            ------------------------------------------------------------
            ping: 
            
            data: {"type":"HystrixCommand","name":"UserFeignClient#findUser(Long)",
            "group":"microservice-provider-user", ......
            ......
            ------------------------------------------------------------
        ```
## 六、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上
* 2、在程序的启动类加上注解 **@EnableCircuitBreaker** 能够 启动 hystrix 断路器功能,高并发 Feign 调用访问下；hystrix实现
线程池隔离、信号量隔离、熔断、降级回退
* 3、自定义 ribbon 负载均衡 配置类不应该和SpringBoot启动类平级，也不能在启动类管辖范围内的包中
* 4、如果要让 **RestTemplate** 具备负载调用微服务功能，那么需要在 SpringBoot 启动类，配置 @Bean 的方法 restTemplate() 上 
加入注解  **@LoadBalanced** ；该注解是由 ribbon 提供
* 5、如果不自定义配置 Ribbon 的负载算法，那么默认是轮询负载

## 七、关于类的注释：
* 1、Controller 层的请求方法 com.baihoomuch.cloud.controller.controller.findUserVo 添加注解  @HystrixCommand(fallbackMethod = "findUserVoFallback")
    + fallbackMethod 为 Hystrix 容错回退的方法；如果微服务调用超时或出错，断路器走另一条分支方法，防止级联失败