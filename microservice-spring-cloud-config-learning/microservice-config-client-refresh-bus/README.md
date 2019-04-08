
### 1、Spring Cloud Config Client 工程构建说明
> 引入组件ID为 org.springframework.cloud 工件ID为 spring-cloud-starter-config 的依赖

> spring Cloud Config 分布式系统外部化配置提供客户端端支持
	
> Spring Cloud Config 配置属性刷新之手动刷新
	
> 安装 Rabbitmq 和 Erlang ,启动 Rabbitmq Server
	
### 2、Spring Cloud Bus 快速开始
> Spring Cloud Bus的工作原理是，如果它在类路径上检测到自身，则添加Spring Boot自动配置。 要启用总线，
请将spring-cloud-starter-bus-amqp或spring-cloud-starter-bus-kafka添加到依赖关系管理中。 Spring Cloud负责其余部
分。 确保代理（RabbitMQ或Kafka）可用并配置。 在localhost上运行时，您无需执行任何操作。 如果您远程运行，请使用
Spring Cloud Connectors或Spring Boot约定来定义代理凭据，如以下Rabbit示例所示：

    ```$xslt
        ------------------------------------------------------------
        application.yml. 
        spring:
          rabbitmq:
            host: mybroker.com
            port: 5672
            username: user
            password: secret
        ------------------------------------------------------------
    ```	

		
> 总线当前支持向所有侦听节点或特定服务的所有节点发送消息（由Eureka定义）。 /bus/* actuator命名空间有一些HTTP端点。
目前，有两个已实施。 第一个/ bus / env发送键/值对来更新每个节点的Spring环境。 第二个/ bus / refresh重新加载每个应
用程序的配置，就好像它们都已在其/ refresh端点上进行了ping操作一样。
		
> 注意：Spring Cloud Bus的初学者包括 Rabbit 和 Kafka ，因为这是两种最常见的实现。 但是，Spring Cloud Stream非常
灵活，绑定器可以与spring-cloud-bus一起使用。
	
### 3、测试顺序
	
* 3.1 半自动，动态刷新配置

* 3.1、启动  microservice-config-server 模块服务，启动端口8080；
* 3.2、microservice-config-client-refresh-bus 项目右击  ===》Run As ===》 Run Configurations ===》单个模块创建多个启动
* 3.3、启动  microservice-config-client-refresh-bus 模块服务，启动端口8085；
* 3.4、启动  microservice-config-client-refresh-bus 模块服务，启动端口8086；
* 3.5、网页页签，输入http://localhost:8085/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	
        ------------------------------------------------------------
    ```
* 3.6、网页页签，输入http://localhost:8086/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	
        ------------------------------------------------------------
    ```
* 3.7、打开  git 克隆到本地的仓库（GitRepos\microservice-config-repo），更新foobar.yml文件内容，在push提交：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	-refresh
        ------------------------------------------------------------
    ```
* 3.8、打开git bash 输入以下内容：
    ```$xslt
        ------------------------------------------------------------
        curl -X POST http://localhost:8085/actuator/bus-refresh
        ------------------------------------------------------------
    ```
* 3.9、网页页签，再次输入http://localhost:8085/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	-refresh
        ------------------------------------------------------------
    ```
* 3.10、网页页签，再次输入http://localhost:8086/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	-refresh	
        ------------------------------------------------------------
    ```
 2.2 全自动，动态刷新配置