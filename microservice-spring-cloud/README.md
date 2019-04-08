## eureka官方说明：
> http://cloud.spring.io/spring-cloud-static/Finchley.RELEASE
		
## 项目学习顺序：
#### 总工程 microservice-spring-cloud
	
#### 1. microservice-provider-user      ======>>    用户服务提供者 
> * **注册eureka客户端服务；**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
		
#### 2. microservice-provider-goods     ======>>    产品服务提供者
> * **注册eureka客户端服务l；**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
#### 3. microservice-consumer-movie     ======>>    用户服务消费者
> * **注册eureka客户端服务；**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
#### 4. microservice-discovery-eureka   ======>>    eureka 

> * **服务集群，发现服务**
		
#### 5. microservice-consumer-movie-ribbon ======>> ribbon
> * **注册eureka客户端服务**
> * **配置对象初始化的方式，实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
#### 6. microservice-consumer-movie-ribbon-customizing-properties
> * **注册eureka客户端服务**
> * **自定义配置文件实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
#### 7. microservice-consumer-movie-ribbon-without-eureka
> * **注册eureka客户端服务**
> * **不关联eureka集群化的多个provider的节点(端口：任意个)，指定ribbon负载 访问的provider（端口:7905）节点**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
																	
#### 8. microservice-consumer-movie-feign
> * **注册eureka客户端服务**
> * **feign已经集成了ribbon负载均衡**
> * **feign实现微服务远程调用（相当于webservice的调用）**
																	
#### 9. microservice-consumer-movie-feign-customizing
> * **注册eureka客户端服务**
> * **feign 已经集成了ribbon负载均衡**
> * **feign 实现微服务远程调用（相当于webservice的调用）**
> * **feign 覆写Fegion的默认配置及Fegion的日志，使用feign自己的http注解协议**

#### 10. microservice-discovery-eureka-ha 
> * **eureka 高可用集群发现及相互注册**
> * **服务集群，发现服务**

#### 11. microservice-consumer-movie-ribbon-with-hystrix
> * **注册eureka客户端服务**
> * **配置对象初始化的方式，实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
> * **加入hystrix断路器模式应用

#### 12. microservice-consumer-movie-ribbon-with-hystrix-propagating
> * **注册eureka客户端服务**
> * **配置对象初始化的方式，实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
> * **加入hystrix断路器模式应用,传播安全上下文或使用Spring作用域**
	
#### 13. microservice-consumer-movie-feign-with-hystrix
> * **注册eureka客户端服务**
> * **feign已经集成了ribbon负载均衡**
> * **feign实现微服务远程调用（相当于webservice的调用）**
> * **整合feign加入hystrix断路器模式**

#### 14. microservice-consumer-movie-feign-customizing-without-hystrix
> * **注册eureka客户端服务**
> * **feign 已经集成了ribbon负载均衡**
> * **feign 实现微服务远程调用（相当于webservice的调用）**
> * **feign 覆写Fegion的默认配置及Fegion的日志，使用feign自己的http注解协议**		
> * **整合feign加入hystrix断路器模式**

#### 15. microservice-consumer-movie-feign-with-hystrix-hystrix-factory
> * **注册eureka客户端服务**
> * **feign 已经集成了ribbon负载均衡**
> * **feign 实现微服务远程调用（相当于webservice的调用）**
> * **feign 覆写Fegion的默认配置及Fegion的日志，使用feign自己的http注解协议**		
> * **整合feign加入hystrix断路器模式（工厂模式构造断路器备用值，Feign使用fallbackFactory属性打印fallback异常）**

#### 16. microservice-hystrix-dashboard
> * **聚合Hystrix的各个断路器，仪表板图形化详情展示。Hystrix Dashboard时监控Hystrix的熔断器的一个组件，提供了数据监控和
> 友好的展示界面**	

#### 17. microservice-hystrix-turbine
> * **使用Turbine聚合监控在使用Hystrix Dashboard组件监控服务的熔断器状况时，每个服务都有一个Hystrix Dashboard主页，服务
> 数量过多时，监控非常不方便。Netflix开源了另一个组件Turbine，用于聚合多个Hystrix Dashboard，将数据显示在一个页面上，集
> 中监控。**	

#### 18. microservice-consumer-movie-ribbon-with-hystrix2 
> * **注册eureka客户端服务**
> * **配置对象初始化的方式，实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
> * **加入hystrix断路器模式应用**

#### 19. microservice-hystrix-turbine2
> * **使用Turbine聚合监控在使用Hystrix Dashboard组件监控服务的熔断器状况时，每个服务都有一个Hystrix Dashboard主页，服务
> 数量过多时，监控非常不方便。Netflix开源了另一个组件Turbine，用于聚合多个Hystrix Dashboard，将数据显示在一个页面上，集
> 中监控。**

#### 20. microservice-consumer-movie-ribbon-with-hystrix3
> * **注册eureka客户端服务**
> * **配置对象初始化的方式，实现如何ribbon 以某个算法类来负载均衡（比如随机负载，轮询负载）**
> * **SpringBoot内部对象RestTemplate对象实现微服务调用（相当于webservice的调用）**
> * **加入hystrix断路器模式应用**

#### 21. microservice-hystrix-turbine3 
> * **使用Turbine聚合监控在使用Hystrix Dashboard组件监控服务的熔断器状况时，每个服务都有一个Hystrix Dashboard主页，服务
> 数量过多时，监控非常不方便。Netflix开源了另一个组件Turbine，用于聚合多个Hystrix Dashboard，将数据显示在一个页面上，集
> 中监控。**

#### 22. microservice-gateway-zuul 
> * **路由器和过滤器：Zuul,路由是微服务架构不可或缺的一部分。例如，/ 可能会映射到您的Web应用程序，/ api / users映射到用
> 户服务和/ api / shop映射到商店服务。Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。**
#### 23. microservice-gateway-zuul-reg-exp 
> * **路由器和过滤器：Zuul,路由是微服务架构不可或缺的一部分。例如，/ 可能会映射到您的Web应用程序，/ api / users映射到用
> 户服务和/ api / shop映射到商店服务。Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。**
> * **使用regexmapper在serviceId和路由之间提供约定。 它使用正则表达式命名组从serviceId中提取变量并将它们注入路由模式**
			
#### 23. microservice-provider-uploadfile
> * **springcloud + zuul 上载文件，涉及上载文件相关配置**

#### 25. microservice-consumer-uploadfile-feign-with-hystrix	 
> * **微服务调用文件上载管理**
		
#### 26. microservice-gateway-zuul-fallback	 
> * **实践zuul的回退，当某个应用无法被代理时，zuul如何回退?**
						
#### 27. microservice-sidecar 
> * **集成非java服务端（php,node.js,paython）到spring cloud 生态圈中**
		
#### 28. microservice-sidecar-test 
> * **测试RestTemplate访问集成非java服务端的应用（microservice-sidecar）**
						