
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

 当前工程为用户消费者者微服务 （microservice-consumer-movie-feign-customizing-without-hystrix）
 
## 四、当前工程是,电影消费者微服务案例,声明式调用 自定义配置的Feign

> Feign受Retrofix、JAXRS-2.0和WebSocket影响，采用了声明式API接口的风格，将Java Http客户端绑定到他的内部。Feign的首要目标
是将Java Http客户端调用过程变得简单。
	
> Feign是一个声明性的Web服务客户端。它使编写Web服务客户端变得更容易。feign已经集成了Ribbon客户端负载功能，所以使用feign
时可无需考虑客户端的负载。

> 覆写Fegion的默认配置及Fegion的日志，使用feign自己的http注解协议

## 五、整合feign加入hystrix断路器模式
> 断路器模式就像是那些容易导致错误的操作的一种代理。这种代理能够记录最近调用发生错误的次数，然后决定使用允许操作继续，
> 或者立即返回错误。
	
* 1、健康指标 SpringBoot 2.0 + 以上配置
    ```
    # feign的hystrix, 启用断路器模式
    feign:
      hystrix:
        enabled: true
    #springboot2.0. 以上的配置项为：
    #actuator端口
    management:
      server:
        port: 9004
      endpoints:
        web:
       #   base-path: / #修改访问路径  2.0之前默认是/   2.0默认是 /actuator  可以通过这个属性值修改
          exposure:
            include: '*'  # 开放所有页面节点  默认只开启了health、info两个节点
    ```
* 2、连接的断路器的状态也在调用应用程序的上下文路径 /health 中公开，如下面的示例所示：
    + 网页页签，输入 http://localhost:9004/actuator/health，网页显示内容如下：
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
	+ 网页页签，输入 http://localhost:7918/msmovie/contro/movie/1，网页显示内容如下：
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
	+ 网页页签，输入 http://localhost:9004/actuator/hystrix.stream，网页显示内容如下：
        ```
            ------------------------------------------------------------
            ping: 
            
            data: {"type":"HystrixCommand","name":"UserFeignClient#findUser(Long)",
            "group":"microservice-provider-user", ......
            ......
            ------------------------------------------------------------
        ```
			
	3.3. 自定义hystrix细化断路器模式
	
## 六、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上
* 2、在程序的启动类加上注解 **@EnableFeignClients** 开启 Feign Client 功能
* 3、在程序的启动类加上注解 **@EnableCircuitBreaker** 能够 启动 hystrix 断路器功能,高并发 Feign 调用访问下；hystrix实现
线程池隔离、信号量隔离、熔断、降级回退

## 七、关于类的注释：
* 1、组件层类 com.baihoomuch.cloud.microservice.UserFeignClient
    + 1.1、在程序加上注解 **@Component** 和 **@FeignClient(value="microservice-provider-user", configuration=FeignConfig.class ,fallback=HystrixClientFallback.class)**
      - 调用 microservice-provider-user 工程项服务 该程序已经具备了Feign的功能
      - 在 UserFeignClient 接口上加 @FeignClient 注解来声明一个Feign Client;
        > value 为远程调用的微服务 ApplicationName;
        
        > configuration 为配置类；作用可使 feign 在远程调用失败后会进行重试。
        
        > fallback 为配置出现错误后 回退 的配置类
      - 在 UserFeignClient 内部有一个findUser()的方法，该方法通过 Feign 来调用 microservice-provider-user 服务的"/findUser/{objectid}"的API接口。
        
    + 1.2、Feign 的配置类 com.baihoomuch.config.FeignCustomizingConfiguration
      - 在程序加上注解 **@Configuration**，表明这是一个配置类
      - 该配置类的 BeanName 方法feignContract();feign 支持springmvc http注解协议,而该 Bean 方法配置告知 Feigin 使用自己
      的http注解协议
        
    + 1.3、Feign 的回退类 com.baihoomuch.cloud.microservice.UserFeignClientFallback
      - 在程序加上注解 **@Component**，表明这是一个组件类
      - 实现 UserFeignClient
      
* 2、组件层类 com.baihoomuch.cloud.microservice.MicroserviceFeignClient
    + 2.1、在程序加上注解 **@Component** 和 **@FeignClient**(name="eureka-service" , url="http://localhost:8761/" , 
    configuration= {FeignConfig.class,FeignCustomizingConfiguration2.class},fallback=MicroserviceFeignClientFallback.class)
      - 调用 eureka-service 工程项服务 该程序已经具备了Feign的功能
      - 在 UserFeignClient 接口上加 @FeignClient 注解来声明一个Feign Client;
        > name(value 的 别名) 为远程调用的微服务 ApplicationName;
        
        > url 为 Eureka Server 端的访问地址；
        
        > configuration 为配置类(可多配置多个)；作用可使 feign 在远程调用失败后会进行重试；作用解决没有权限直接访问eureka服务端，
        feign需要账号密码方可访问
        
        > fallback 为配置出现错误后 回退 的配置类
      - 在 MicroserviceFeignClient 内部有一个 findServiceInfoFormEurekaByApplicationName()的方法，该方法通过 Feign 来调用 
      eureka-service 服务的"/eureka/apps/{applicationName}"的API接口，功能根据注册到 eureka server 端的 ApplicationName，
      获取该 微服务 的详情信息
      
## 八、异常总结：
* 1、feign.FeignException: 
    > status 404 reading MicroserviceFeignClient#findServiceInfoFormEurekaByApplicationName(String)
    
    该抛出404异常，字面异常诠释：没有权限直接访问eureka服务端，需要账号密码方可访问
    
* 2、解决第一次请求报超时异常的方案
    ```
    hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000
    ```
	或者：
    ```
	hystrix.command.default.execution.timeout.enabled: false
    ```
	或者：
    ```
	feign.hystrix.enabled: false ## 索性禁用feign的hystrix
    ```
    > 超时的issue：https://github.com/spring-cloud/spring-cloud-netflix/issues/768
    
    > 超时的解决方案： http://stackoverflow.com/questions/27375557/hystrix-command-fails-with-timed-out-and-no-fallback-available
        
    > hystrix配置： https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.thread.timeoutInMilliseconds
        