
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

 当前工程为用户消费者者微服务 （microservice-consumer-movie）
 
## 四、异常解析 
 1. com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException 详情如下：
    ```
    Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check 
    the manual that corresponds to your MySQL server version for the right syntax to use near 'type=MyISAM' at line 1
    ```
    > 出现該异常，检查数据库版本，即yml配置JPA MySQL方言针对的数据库版本
    
## 四、测试顺序

* 1、启动  microservice-discovery-eureka 模块服务，启动端口8761；
* 2、启动  microservice-provider-user 模块服务，启动端口7901；
* 3、启动  microservice-consumer-user 模块服务，启动端口7911；
* 4、网页页签，输入 http://localhost:7901/msprovider/userContro/findUser/1，网页显示内容如下：
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
* 5、网页页签，输入 http://localhost:7911/msmovie/contro/movie/1，网页显示内容如下：
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
## 五、测试总结
* 1、在程序的启动类加上注解 **@EnableEurekaClient** 能够 eureka 客户端注册到 eureka 服务端上

* 2、application.yml 配置文件配置微服务远程路径，记住有这种方式不建议采用
    ```
    user:
      msremotepath: http://127.0.0.1:7901/msprovider/userContro/findUser/
    ```
* 3、eureka.client.registerWithEureka ：表示是否将自己注册到Eureka Server，默认为true。由于当前这个应用就是Eureka Server，
故而设为false

* 4、eureka.client.fetchRegistry ：表示是否从Eureka Server获取注册信息，默认为true。因为这是一个单点的Eureka Server，不
需要同步其他的Eureka Server节点的数据，故而设为false。

* 5、eureka.client.serviceUrl.defaultZone ：设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是
http://localhost:8761/eureka ；多个地址可使用 , 分隔。