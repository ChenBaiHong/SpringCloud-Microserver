package com.baihoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * 路由是微服务架构不可或缺的一部分。例如，/ 可能会映射到您的Web应用程序，
 * / api / users映射到用户服务和/ api / shop映射到商店服务。
 * Zuul是Netflix的基于JVM的路由器和服务器端负载均衡器。
 */
@SpringBootApplication
@EnableZuulProxy //启用嵌入式Zuul反向代理，这样做会导致本地呼叫转发到适当的服务。注意：zuul 会默认代理所有注册到eureka的应用ID
public class MicroserviceGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceGatewayZuulApplication.class, args);
    }
    /**
     * 使用regexmapper在serviceId和路由之间提供约定。 
     * 它使用正则表达式命名组从serviceId中提取变量并将它们注入路由模式
     * <br>
     *  microservice-provider-user-v1 示例：
     *  	microservice-gateway-zuul-reg-exp代理访问 url: http://localhost:8040/v1/microservice-provider-user/msprovider/userContro/findUser/1
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
            "(?<name>^.+)-(?<version>v.+$)",  // service应用正则
            "${version}/${name}"); //route模式正则
    }
}
