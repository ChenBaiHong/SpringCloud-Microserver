package com.baihoo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
/**
 * 
 * @author Administrator
 *	在程序的启动类加上注解@EnableFeignClients开启Feign Client功能
 */
@ServletComponentScan // 允许扫描Servlet组件（{@link WebFilter filters}，{@ link WebServlet servlets}和{@link WebListener listeners}）。 仅在使用时执行扫描嵌入式Web服务器。
@SpringBootApplication
@EnableEurekaClient 	// 声明式Eureka客户端，Eureka 服务端好集群管理
@EnableFeignClients 		// 声明式feign客户端，【替代RestTemplate，实现微服务调用】
@EnableCircuitBreaker  // hystrix 断路器
@EnableTransactionManagement // 声明该注解，作用解决Fegin调用接口时传递的参数过大，无法调用成功
public class UploadfileApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UploadfileApplication.class, args);
    }
}

