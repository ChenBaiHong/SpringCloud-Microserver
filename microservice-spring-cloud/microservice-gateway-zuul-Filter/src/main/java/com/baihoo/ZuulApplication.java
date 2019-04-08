package com.baihoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.baihoo.filter.PreZuulFilter;

/**
 * 路由是微服务架构不可或缺的一部分。例如，/ 可能会映射到您的Web应用程序，
 * / api / users映射到用户服务和/ api / shop映射到商店服务。
 * Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。
 */
@SpringBootApplication
@EnableZuulProxy //启用嵌入式Zuul反向代理，这样做会导致本地呼叫转发到适当的服务。注意：zuul 会默认代理所有注册到eureka的应用ID
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
    /**
     * 启动类中，Zuul过滤器配置Bean
     * @return
     */
    @Bean
    public PreZuulFilter preZuulFilter() {
    	
    	return new PreZuulFilter();
    }
}
