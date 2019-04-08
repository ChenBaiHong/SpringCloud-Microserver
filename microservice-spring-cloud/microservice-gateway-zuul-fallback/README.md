## 一、路由器和过滤器概念
> 路由器和过滤器：Zuul,路由是微服务架构不可或缺的一部分。例如，/ 可能会映射到您的Web应用程序，/ api / users映射到用户服
务和/ api / shop映射到商店服务。Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。

>Netflix使用Zuul进行以下操作：
认证，洞察，压力测试，金丝雀测试，动态路由，服务迁移，负载脱落，安全，静态响应处理，主动/主动流量管理

> Zuul的规则引擎允许规则和过滤器基本上以任何JVM语言编写，内置支持Java和Groovy。
 
> 要在项目中包含Zuul，引入组ID为 **org.springframework.cloud** 工件ID为 **spring-cloud-starter-netflix-zuul** 的依赖。

> 所有路由的默认 **Hystrix** 隔离模式（**ExecutionIsolationStrategy**）都是 **SEMAPHORE**。 如果首选隔离模式，则可以将  
**zuul.ribbonIsolationStrategy** 更改为**THREAD**。

注意：zuul 会默认代理所有注册到eureka的应用ID

## 二、测试顺序
* 1、启动  microservice-discovery-eureka 模块服务，启动端口8761；
* 2、启动  microservice-provider-user 模块服务，启动端口7901；
* 3、启动  microservice-gateway-zuul-fallback 模块服务，启动端口8040；
* 4、网页页签，输入 [ http://localhost:8040/microservice-provider-user/msprovider/userContro/findUser/1 ];
网页显示内容如下：
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
* 5、停止  microservice-provider-user 模块服务，停止端口7901；
* 4、网页页签，输入 [ http://localhost:8040/microservice-provider-user/msprovider/userContro/findUser/1 ];
网页显示内容如下：
    ```
        ------------------------------------------------------------
        服务不可用，请稍后再试~~!
        ------------------------------------------------------------
    ```
## 三、测试总结
* 1、在程序的启动类加上注解 **@EnableZuulProxy** 能够 启用嵌入式Zuul反向代理，这样做会导致本地呼叫转发到适当的服务。
* 2、实践zuul的回退，当某个应用无法被代理时，zuul如何回退？
    + 配置 com.baihoo.fallback.MyFallbackProvider 实现 FallbackProvider，成为 zuul 回退组件类
			