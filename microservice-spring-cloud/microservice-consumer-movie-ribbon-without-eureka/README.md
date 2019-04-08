
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

> 当前工程是（microservice-consumer-movie-ribbon-without-eureka） 电影消费者微服务案例, 整合ribbon负载均衡

> Eureka Client 是一个Java客户端，用于简化与 Eureka Server 的交互，客户端同时也具备一个内置的、使用轮询（round-robin）
  负载算法的负载均衡器。
  
## 四、自定义实现 ribbon 负载不关联注册到 Eureka Server 的提供端节点 而采用 application.yml 中的配置
1、application.yml 配置如下：
```
# 将ribbon.eureka.Enable属性设置为false显式禁止在Ribbon中使用Eureka。
ribbon:
  eureka:
    enabled: false
# Eureka 是一种抽象远程服务器发现的方便方法，这样您就不必在客户端中硬编码它们的URL，如果不想使用Eureka集成Ribbon 和 Feign 工作。   
# 假设您已经为“商店（microservice-provider-user）”声明了@RibbonClient，而Eureka没有使用(甚至在类路径上也没有)。
# Ribbon客户端默认为已配置的服务器列表。按以下方式提供配置
microservice-provider-user: 
  ribbon:
    listOfServers: localhost:7904 #因此只会访问7904的节点
```
2、测试说明：微服务消费端 可根据定义的负载算法调用 多个微服务提供端的任意节点
  + MICROSERVICE-PROVIDER-USER [微服务ApplicationName应用名]	
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7901 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7902 [端口]
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7903 [端口] , 
	- XS-20171114WLQY [电脑全名称] : microservice-provider-user [微服务ApplicationName应用名] : 7904 [端口]
## 五、测试顺序

* 1、启动  microservice-discovery-eureka 模块服务，启动端口8761；
* 2、启动  microservice-provider-user 模块服务，启动端口7901；
* 3、启动  microservice-provider-user 模块服务，启动端口7902；
* 4、启动  microservice-provider-user 模块服务，启动端口7903；
* 5、启动  microservice-provider-user 模块服务，启动端口7904；
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
* 8、网页页签，输入 http://localhost:7914/msmovie/contro/movie/1，网页显示内容如下：
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
* 9、网页页签，回车访问 6 次该地址；http://localhost:7914/msmovie/contro/movie/1，后台结果：
    + microservice-provider-user:7904     ======      被负载调用 6次
      
## 六、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上
* 2、如果要让 **RestTemplate** 具备负载调用微服务功能，那么需要在 SpringBoot 启动类，配置 @Bean 的方法 restTemplate() 上 
加入注解  **@LoadBalanced** ；该注解是由 ribbon 提供
* 3、如果不自定义配置 Ribbon 的负载算法，那么默认是轮询负载