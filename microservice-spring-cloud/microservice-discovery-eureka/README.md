## 一、 当前工程是 eureka server 端

http://localhost:8761/eureka/apps url详情说明：
> eureka 内部url访问，获取当前那些服务工程的详情信息以xml格式返回（可以../apps/服务应用名称，返回单一的某个微服务工程详情）
		
## 二、 异常总结	
* 发现问题：
> Spring Boot2中Spring Security导致Eureka注册失败
* 解决问题：					
> https://www.chenbaihoo.com/archives/eureka-register-failed