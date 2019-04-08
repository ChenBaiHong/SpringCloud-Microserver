####一、 当前工程是 eureka server端
http://localhost:8761/eureka/apps url详情说明：
> eureka 内部url访问，获取当前那些服务工程的详情信息以xml格式返回（可以../apps/服务应用名称，返回单一的某个微服务工程详情）

####二、 eureka server端，高可用配置 （High Availability, Zones and Regions）
* 高可用诠释：
    > eureka 服务器没有后端存储，但是注册表中的 服务实例 都必须发送 心跳 以保持其注册更新(因此这可以在内存中完成)。客户端也
    有一个 eureka 注册 的 内存缓存 (这样他们就不必对服务的每个请求都转到注册中心了)。默认情况下，每个eureka服务器也是 eureka
    客户端，需要(至少一个) 服务url 来定位对等服务器。您不提供该服务，则该服务将运行并工作，但它会在日志中填充大量关于无法向
    对等方注册的噪音。

    > 有关区域和区域客户端的带状支持的详细信息，请参见下面的内容:
    [multi_spring-cloud-ribbon.html](http://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html)
    
	> 通过运行多个实例并要求它们相互注册，Eureka可以变得更加灵活和可用。
	
* 什么是 Peer Awareness ，做什么？
    > **在下面的示例中，我们有一个YAML文件，它可以在两个主机(peer 1和peer 2)上运行相同的服务器，方法是在不同的配置文件中运行它。
    我们可以使用此配置来通过操作/etc/host（windows 10: C:\WINDOWS\system32\drivers\etc\HOST ）设置本地解析主机名 来测试 单个
    主机上的 对等感知 (在生产中这样做没有多大价值)。实际上，如果在知道自己主机名的机器上运行(默认情况下，
    使用java.net.inetAddress查找)，则不需要eureka.instance.hostname**
         
    1、HOST 文件配置如下： 
     ```
     # localhost name resolution is handled within DNS itself.
     127.0.0.1 peer1 peer2 peer3
     ```  
    2、application.yml 文件配置如下：
     ```
        ---
        spring:
          profiles: peer1
        eureka:
          instance:
            hostname: peer1
          client:
            serviceUrl:
              defaultZone: http://peer2/eureka/
              
        ---
        spring:
          profiles: peer2
        eureka:
          instance:
            hostname: peer2
          client:
            serviceUrl:
              defaultZone: http://peer1/eureka/
     ```
     > 注意：目前高可用的eureka得去除springsecurity依赖详情参阅：
       [multi_spring-cloud-eureka-server.html](http://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html)

####二、 eureka client 端，注册到 配置的多个 eureka server 端
```
# 通过在类路径上拥有SpringCloudStart-Netflix-eureka客户机，您的应用程序将自动向Eureka服务器注册。要找到Eureka服务器，需要进行配置
eureka:
  client:
    service-url:
      # eureka默认铺设到指定 URL 上,eureka单机模式下含权限
      defaultZone: http://admin:admin@localhost:8761/eureka/
#      defaultZone: http://peer1:8761/eureka/,http://peer2:8762/eureka/,http://peer3:8763/eureka/                     
    #eureka的健康检查  
    healthcheck:
      enabled: true
```