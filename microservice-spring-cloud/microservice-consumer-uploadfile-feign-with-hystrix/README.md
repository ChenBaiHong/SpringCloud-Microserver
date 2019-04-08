##### 一、 通过Zuul上传文件

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

##### 二、 异常总结
* 发现问题：
    > Caused by: java.net.ConnectException: Connection refused: connect
* 解决问题：
    > 说明如果通过zuul代理访问应用出现该异常 ，那么请注意有可能yaml文件中配置了固定的IP地址，请注释该配置
    
##### 三、 MongoDB 文件服务器 提供者微服务 案例测试顺序如下：
1.启动 microservice-discovery-eureka 模块服务，启动1个端口8761；

2.启动microservice-provider-uploadfile 模块服务，启动1个端口8050；
  + 2.1.新起网页页签，输入http://localhost:8050/ ，网页显示MongoDB 文件服务 首页
  
3.启动microservice-consumer-uploadfile-feign-with-hystrix 模块服务，启动1个端口8061；
  + 3.1.新起网页页签，输入http://localhost:8061/ ，网页显示MongoDB 文件服务首页【页面功能操作由Feign调用当前文件服务器
  Control】
  
##### 四、测试总结
* SpringCloud 使用 Feign实现服务间调用由于返回数据过大，无法调用成功解决之法
    > https://www.chenbaihoo.com/archives/springcloud-feign-databig
* SpringCloud 使用 Feign调用传入复杂参数，无法正确被调用
    > https://www.chenbaihoo.com/archives/springcloud-feign-comdata
* SpringCloud 使用 Feign 微服务接口实现文件上传下载
    > https://www.chenbaihoo.com/archives/springcloud-feign-uploadfile
    
##### 五、异常总结
1、解决 vue-resource 跨域问题：
  + 错误场景：
    - 前端是vue 工程，写的是绝对 URL 请求后端工程接口，报错如下：
      > No 'Access-Control-Allow-Origin' header is present on the requested resource
    
  + 解决方法:
    - 后端开放跨域；新增一个过滤器 (Filter)，改写所有请求头信息
      > - 编写 com.baihoo.cloud.filter.RequestFilter 类实现 javax.servlet.Filter
      
      > - 实现的过滤器类 添加注解 **@Order(1)** (@order 代表注解表示执行过滤顺序，值越小，越先执行) 和
     **@WebFilter**(filterName = "requestFilter",urlPatterns = {"/*"})
    - SpringBoot启动 添加 注解 **@ServletComponentScan**
      > 允许扫描Servlet组件（{@link WebFilter filters}，{@ link WebServlet servlets}和{@link WebListener listeners}）。
       仅在使用时执行扫描嵌入 Web 服务器