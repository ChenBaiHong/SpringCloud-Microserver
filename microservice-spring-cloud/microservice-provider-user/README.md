
## 一、微服务概念
> 微服务构建的是分布式系统，各个微服务之间通过网络进行通信。一般我们用服务提供者和服务消费者来描述微服务之间的调用关系

* 服务提供者	-|- 服务被调用方
* 服务消费者	-|- 服务的调用方
		
> 以电影院售票系统为例，用户从电影售票系统发起购票请求，在进行购买业务之前，售票系统需要先调用用户微服务接口，查看用户的
> 相关信息是否符合购买标准等相关信息，这里，用户微服务就是服务的提供者，售票系统微服务就是服务消费者。

## 二、编写服务提供者
	
 当前工程为用户提供者微服务 （microservice-provider-user）

## 三、编写服务消费者

 用户消费者者微服务 （microservice-consumer-user）
 
## 四、异常解析 
 1. com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException 详情如下：
    ```
    Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check 
    the manual that corresponds to your MySQL server version for the right syntax to use near 'type=MyISAM' at line 1
    ```
    > 出现該异常，检查数据库版本，即yml配置JPA MySQL方言针对的数据库版本



