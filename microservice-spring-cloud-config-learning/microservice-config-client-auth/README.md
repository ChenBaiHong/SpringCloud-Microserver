### 1、Spring Cloud Config Client 工程构建说明
> 引入组件ID为 org.springframework.cloud 工件ID为 spring-cloud-starter-config 的依赖

> spring Cloud Config 分布式系统外部化配置提供客户端端支持

> Spring Cloud Config 客户端，安全密码用户链接 Config Server
		
### 2、测试顺序
	
* 2.1、启动  microservice-config-server-auth 模块服务，启动端口8080；
* 2.2、启动  microservice-config-client-auth 模块服务，启动端口8083；
* 2.3、新起网页页签，输入 http://localhost:8083/ ，网页显示如下输入用户名和密码，用户名默认user,密码后台随机生成
		