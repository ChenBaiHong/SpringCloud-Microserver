#### 1、Spring Cloud Config Server 工程构建说明
> 引入组件ID为 org.springframework.cloud 工件ID为 spring-cloud-config-server 的依赖

> spring Cloud Config 分布式系统外部化配置提供服务端支持

> Spring Cloud Config Server为外部配置（名称 - 值对或等效的YAML内容）提供基于HTTP资源的API。 通过使用@EnableConfigServer注
释，Spring-Cloud-Config 服务器就可嵌入Spring Boot应用程序中。

> Spring Cloud Config Server 添加安全认证链接机制

#### 2、Spring Cloud Config 配置安全认证
> 我们可以为Config Server（从物理网络安全到OAuth2承载令牌）提供保护，因为Spring Security和Spring Boot为许多安全安排提供支持。
	
> 要使用默认的Spring Boot配置的HTTP Basic安全性，请在类路径中包含Spring Security（例如，通过spring-boot-starter-security依赖引用）。
 默认值为用户名和随机生成的密码。 随机密码在实践中没有用，因此建议配置自己的管理密码（通过设置spring.security.user.password）
 并对其进行加密
 
#### 3、测试顺序
* 3.1、启动  microservice-config-server-auth 模块服务，启动端口8080；
* 3.2、新起网页页签，输入 http://localhost:8080/ ，网页显示如下输入用户名和密码，用户名默认user,密码后台随机生成
			
			
