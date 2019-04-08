
#### 一、集成非java服务端（php,node.js,paython）到spring cloud 生态圈中(比如集成 php 后端服务)（正常情况测试）：
	 
 * 1、启动 microservice-discovery-eureka 模块服务，启动1个端口8761；
 * 2、启动 microservice-gateway-zuul 模块服务，启动1个端口8040；
 * 3、启动 microservice-sidecar 模块服务，启动1个端口8070；
 * 4、启动  microservice-sidecar/mscserver-node/msc-node.js 后端 服务，启动1个端口8060
    + 需要注意的是；msc-node.js 的微服务应用必须实现一个 /health 健康检查接口，Sidecar 应用会每隔几秒访问一次该接口，
    并将该服务的健康状态返回给 Eureka，该接口只需要返回 **{ status: 'UP' }** 这样一串Json即可
 * 6、新起网页页签，输入 http://localhost:8060/ ，网页显示如下:
    ```
    {
        index: "欢迎来到首页"
    }
    ```
      
 * 7、新起网页页签，然后输入 http://localhost:8060/health ，网页显示如下:
     ```
     {
         status: "UP"
     }
     ```
 * 8、新起网页页签，然后输入http://localhost:8070/ ，访问 Sidecar 的首页，提供了三个接口，网页显示如下:
      ```
      ping
      health
      hosts/microservice-sidecar
      ```
 * 9、新起网页页签，然后输入http://localhost:8040/microservice-sidecar 或者 http://localhost:8040/zuul/microservice-sidecar，zuul 代理  sidecar 异构平台的微服务网络接口地址，网页显示如下:
     ```
     {
         index: "欢迎来到首页"
     }
     ```
 * 10、新起网页页签，然后输入http://localhost:8040/microservice-sidecar/health 或者 http://localhost:8040/zuul/microservice-sidecar/health，zuul 代理 sidecar 异构平台的微服务网络接口地址，网页显示如下:
     ```
     {
         status: "UP"
     }
     ```
 * 11、启动 microservice-sidecar-test 模块服务，启动1个端口8071；
 * 12、新起网页页签，然后输入 http://localhost:8071/sidecarTest/msc-node，restTemplate 将会解析 (由Ribbon提供了解析支持)applicationName {microservice-sidecar}的网络地址，由 zuul代理 sidecar异构平台的微服务网络接口地址，网页显示如下:
     ```
     {
         index: "欢迎来到首页"
     }
     ```	
#### 二、测试总结
 * 1、在程序的启动类加上注解 **@EnableSidecar** 能够注册到 eureka 服务端上
 * 2、EnableSidecar 注解能注册到 eureka 服务上，是因为该注解包含了 eureka 客户端的注解，该 EnableZuulProxy 是一个复合注解。
 * 3、@EnableSidecar --> { @EnableCircuitBreaker、@EnableDiscoveryClient、@EnableZuulProxy } 包含了 eureka 客户端注解，
 同时也包含了 Hystrix 断路器模块注解，还包含了 zuul API网关模块。
 * 4、通过在yml配置文件中添加 sidecar 属性，就可以将node.js后端服务添加到SpringCloud生态圈中，完美无缝衔接，配置如下
     ```
     #####################################################################################################
     # 异构微服务的配置， port 代表异构微服务的端口；health-uri 代表异构微服务的操作链接地址
     sidecar:
       port: 8060
       health-uri: http://localhost:${sidecar.port}/health
     #####################################################################################################
     ```