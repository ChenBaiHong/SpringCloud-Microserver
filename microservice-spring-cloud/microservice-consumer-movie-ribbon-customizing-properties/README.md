1. 当前工程是 电影消费者微服务案例, 整合ribbon负载均衡

	-- Eureka Client是一个Java客户端，用于简化与Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。

## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

> 当前工程是（microservice-consumer-movie-ribbon） 电影消费者微服务案例, 整合ribbon负载均衡

> Eureka Client 是一个Java客户端，用于简化与 Eureka Server 的交互，客户端同时也具备一个内置的、使用轮询（round-robin）
  负载算法的负载均衡器。
  	
## 四、自定义 ribbon 配置的 application.yml 文件，实现以那种算法负载均衡

1、当前工程控制层 controller.java 通过RestTemplate，调用“提供端微服务【多个服务提供者】”要实现负载均衡调用, 负载规则通
过application.yml文件配置，配置如下
```
# microservice-provider-user 要被调用的微服务项   
microservice-provider-user: 
  ribbon: 
    # 配置什么规则来负载均衡， 默认提供根据相应时间加权的规则，com.netflix.loadbalancer.WeightedResponseTimeRule
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule   
```
2、测试说明：微服务消费端 可根据定义的负载算法调用 多个微服务提供端的任意节点
  + MICROSERVICE-PROVIDER-USER [微服务ApplicationName应用名]	
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7901 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7902 [端口]
			 
  + MICROSERVICE-PROVIDER-USER2 [微服务ApplicationName应用名]	
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user2 [微服务ApplicationName应用名] : 7903 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user2 [微服务ApplicationName应用名] : 7904 [端口]

## 五、测试顺序

* 1、启动  microservice-discovery-eureka 模块服务，启动端口8761；
* 2、启动  microservice-provider-user 模块服务，启动端口7901；
* 3、启动  microservice-provider-user 模块服务，启动端口7902；
* 4、启动  microservice-provider-user2 模块服务，启动端口7903；
* 5、启动  microservice-provider-user2 模块服务，启动端口7904；
* 6、启动  microservice-consumer-movie-ribbon 模块服务，启动端口7912；
* 7、网页页签，输入 http://localhost:7901|7902|7903|7904/msprovider/userContro/findUser/1，网页显示内容如下：
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
* 8、网页页签，输入 http://localhost:7913/msmovie/contro/movie/1，网页显示内容如下：
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
* 9、网页页签，回车访问 6 次该地址；http://localhost:7913/msmovie/contro/movie/1，后台结果：
    + microservice-provider-user:7901     ======      被负载调用 2次
    + microservice-provider-user:7902     ======      被负载调用 4次
      
* 10、网页页签，输入 http://localhost:7913/msmovieribbon/contro/test，网页显示内容如下：
    ```
        ------------------------------------------------------------
        baiHoo
        ------------------------------------------------------------
    ```
    > 测试目的：如果不自定义配置 Ribbon 的负载算法，那么默认是轮询负载
    
## 六、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上
* 2、如果要让 **RestTemplate** 具备负载调用微服务功能，那么需要在 SpringBoot 启动类，配置 @Bean 的方法 restTemplate() 上 
加入注解  **@LoadBalanced** ；该注解是由 ribbon 提供
* 3、如果不自定义配置 Ribbon 的负载算法，那么默认是轮询负载