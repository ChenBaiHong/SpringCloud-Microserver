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
* 3、启动  microservice-gateway-zuul 模块服务，启动端口8040；
* 4、启动  microservice-consumer-movie-feign-with-hystrix 模块服务，启动端口7915；
* 5、网页页签，输入 http://localhost:7901/msprovider/userContro/findUser/1，网页显示内容如下：
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
* 6、网页页签，输入 http://localhost:7915/msmovie/contro/movie/1，网页显示内容如下：
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
* #### 7、application.yml.demo1 测试顺序
    + 7.1、网页页签，输入 [ http://localhost:8040/microservice-provider-user/msprovider/userContro/findUser/1 ] (通过应用 代理)
    或者 [ http://localhost:8040/user/msprovider/userContro/findUser/1 ](通过指定应用名指定路径 代理)网页显示内容如下：
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
    + 7.2、网页页签，输入 [ http://localhost:8040/microservice-consumer-movie-feign-with-hystrix/msmovie/contro/movie/1 ]，(禁止代理)
    网页显示内容如下：
        ```
            ------------------------------------------------------------
            Whitelabel Error Page
            ......
            ------------------------------------------------------------
        ```	
* #### 8、application.yml.demo2 测试顺序
    + 8.1、网页页签，输入 [ http://localhost:8040/microservice-provider-user/msprovider/userContro/findUser/1 ] (通过应用 代理)
    或者 [ http://localhost:8040/user-path/msprovider/userContro/findUser/1 ](通过指定应用名指定路径 代理)网页显示内容如下：
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
    + 8.2、网页页签，输入 [ http://localhost:8040/microservice-consumer-movie-feign-with-hystrix/msmovie/contro/movie/1 ]，(通过代理)
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
* #### 9、application.yml.demo3 测试顺序
    + 9.1、网页页签，输入 [ http://localhost:8040/user-url/msmovie/contro/movie/1 ]，(指定 IP:PORT 的路径 代理)
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
* #### 10、application.yml.demo4 测试顺序 (Ribbon对多个 提供端服务 的URL 进行负载均衡调用)
    + 10.1、启动  microservice-provider-user 模块服务，启动端口7902；
    + 10.2、网页页签，输入 [ http://localhost:8040/user-url/msprovider/userContro/findUser/1 ]，访问 6次 (指定 应用名 的路径 代理)
    + 10.3、microservice-provider-user 模块服务，端口7901 ；被调用 3 次
    + 10.4、microservice-provider-user 模块服务，端口7902 ；被调用 3 次
    
* #### 11、application.yml.demo5 测试顺序 (Ribbon对多个 提供端服务 的URL 进行负载均衡调用)
    + 11.1、启动  microservice-provider-user 模块服务，启动端口7902；
    + 11.2、网页页签，输入 [ http://localhost:8040/user-url/msprovider/userContro/findUser/1 ]，访问 6次 (指定 应用名 的路径 代理)
    + 11.3、microservice-provider-user 模块服务，端口7901 ；被调用 3 次
    + 11.4、microservice-provider-user 模块服务，端口7902 ；被调用 3 次
    
* #### 12、application.yml.demo6 测试顺序 (设置 Zuul 代理前缀 /api ) 
    + 12.1、网页页签，输入 [ http://localhost:8040/api/microservice-provider-user/msprovider/userContro/findUser/1 ] 
    (通过应用 代理)网页显示内容如下：
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
    + 12.2、网页页签，输入 [ http://localhost:8040/api/microservice-consumer-movie-feign-with-hystrix/msmovie/contro/movie/1 ]，(代理)
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
* #### 13、application.yml.demo7 测试顺序 
    + 13.1、网页页签，输入 [ http://localhost:8040/msprovider/userContro/findUser/microservice-provider-user/1 ] 
    (通过应用路径颠倒 代理)网页显示内容如下：
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
* #### 13、application.yml.demo8 测试顺序(通过 Zuul 代理上传文件) 
    > 如果您使用 @EnableZuulProxy，您可以使用代理路径上传文件，只要文件很小，它就可以工作。 对于大型文件，有一个替代路径绕过
    >“/ zuul / *”中的Spring DispatcherServlet（以避免多部分处理）。 换句话说，如果你有zuul.routes.customers = / customers / **，
    > 那么你可以将大文件POST到/ zuul / customers / *。 servlet路径通过zuul.servletPath外部化。 如果代理路由引导您完成功能区负
    > 载平衡器，则极大文件也需要提升超时设置，如以下示例所示：

    ```
    application.yml
    hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 60000
    ribbon:
      ConnectTimeout: 3000
      ReadTimeout: 60000
    ```
## 三、测试总结
* 1、在程序的启动类加上注解 **@EnableZuulProxy** 能够 启用嵌入式Zuul反向代理，这样做会导致本地呼叫转发到适当的服务。
注意：zuul 会默认代理所有注册到eureka的应用ID

    
    
