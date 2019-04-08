
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

 当前工程为用户消费者者微服务 （microservice-consumer-movie-feign-customizing）
 
## 四、当前工程是,电影消费者微服务案例,声明式调用 自定义配置的Feign
> Feign受Retrofix、JAXRS-2.0和WebSocket影响，采用了声明式API接口的风格，将Java Http客户端绑定到他的内部。Feign的首要目标
是将Java Http客户端调用过程变得简单。
	
> Feign是一个声明性的Web服务客户端。它使编写Web服务客户端变得更容易。feign已经集成了Ribbon客户端负载功能，所以使用feign
时可无需考虑客户端的负载。

> 覆写Fegion的默认配置及Fegion的日志，使用feign自己的http注解协议

## 五、测试顺序

* 1、启动  microservice-discovery-eureka 模块服务，启动端口8761；
* 2、启动  microservice-provider-user 模块服务，启动端口7901；
* 3、启动  microservice-consumer-movie-feign-customizing 模块服务，启动端口7913；
* 4、网页页签，输入 http://localhost:7901/msprovider/userContro/findUser/1，网页显示内容如下：
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
* 5、网页页签，输入 http://localhost:7912/msmovie/contro/movie/1，网页显示内容如下：
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
## 六、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上
* 2、在程序的启动类加上注解 **@EnableFeignClients** 开启 Feign Client 功能
* 3、eureka.client.registerWithEureka ：表示是否将自己注册到Eureka Server，默认为true。由于当前这个应用就是Eureka Server，
故而设为false
* 4、eureka.client.fetchRegistry ：表示是否从Eureka Server获取注册信息，默认为true。因为这是一个单点的Eureka Server，不
需要同步其他的Eureka Server节点的数据，故而设为false。
* 5、eureka.client.serviceUrl.defaultZone ：设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是
http://localhost:8761/eureka ；多个地址可使用 , 分隔。

## 七、关于类的注释：
* 1、组件层类 com.baihoomuch.cloud.microservice.UserFeignClient
    + 1.1、在程序加上注解 **@Component** 和 **@FeignClient(value="microservice-provider-user",configuration=FeignConfig.class)**
        > - 调用 microservice-provider-user 工程项服务 该程序已经具备了Feign的功能
        > - 在 UserFeignClient 接口上加 @FeignClient 注解来声明一个Feign Client。value 为远程调用的微服务 ApplicationName;
        FeignConfig.class为配置类
        > - 在 UserFeignClient 内部有一个findUser()的方法，该方法通过 Feign 来调用 microservice-provider-user 服务的"/findUser/{objectid}"的API接口。
        
    + 1.2、Feign 的配置类 com.baihoomuch.cloud.microservice.config.FeignConfig
        > - 在程序加上注解 **@Configuration**，表明这是一个配置类
        > - 注入一个BeanName为feignRetryer的Retryer的Bean，可使feign在远程调用失败后会进行重试。
        
* 1、自定义 feign 的http注解协议配置类 com.baihoomuch.config.FeignCustomizingConfiguration
    + 1.1、该配置类，属于启用 feign 的默认的 http 注解协议
        > - com.baihoomuch.config.FeignCustomizingConfiguration.feignContract 配置，告知 Feign 使用自己的http注解协议
        > - com.baihoomuch.config.FeignCustomizingConfiguration.feignLoggerLevel 配置 Feign 的日志级别
    + 1.2、配置类一定要脱离程序的启动类（MicroserviceConsumerMovieFeignApplication）的控制范围，否则该启动类控制范围所有
    的请求方式均采用 Feign 默认的http注解协议
    
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