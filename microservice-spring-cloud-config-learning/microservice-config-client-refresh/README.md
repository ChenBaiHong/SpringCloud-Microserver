
### 1、Spring Cloud Config Client 工程构建说明
> 引入组件ID为 org.springframework.cloud 工件ID为 spring-cloud-starter-config 的依赖

> spring Cloud Config 分布式系统外部化配置提供客户端端支持
	
> Spring Cloud Config 配置属性刷新之手动刷新
	
> 当配置发生变化时，标记为@RefreshScope的Spring @Bean会得到特殊处理。此功能解决了有状态bean的问题，只有在初始化时
才会注入其配置。例如，如果数据源在通过环境更改数据库URL时具有打开的连接，则您可能希望这些连接的持有者能够完成他们
正在执行的操作。然后，下次从池中借用某个连接时，它会获得一个带有新URL的连接。
	
> 注意：@RefreshScope（在技术上）可以同时 在@Configuration类上工作，但它可能会导致令人惊讶的行为，不建议这个两个注解在一起使用
	
### 2、测试顺序

* 2.1、启动  microservice-config-server-auth 模块服务，启动端口8080；
* 2.2、启动  microservice-config-client-refresh 模块服务，启动端口8085；
* 2.3、网页页签，输入http://localhost:8085/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	
        ------------------------------------------------------------
    ```
* 2.4、打开  git 克隆到本地的仓库（GitRepos\microservice-config-repo），更新foobar.yml文件内容，在push提交：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	-refresh
        ------------------------------------------------------------
    ```
* 2.5、打开git bash 输入以下内容：
    ```$xslt
        ------------------------------------------------------------
        curl -X POST http://localhost:8085/actuator/refresh
        ------------------------------------------------------------
    ```
* 2.6、网页页签，再次输入http://localhost:8085/profile，网页显示内容如下：
    ```$xslt
        ------------------------------------------------------------
        profile-dev	-refresh
        ------------------------------------------------------------
    ```