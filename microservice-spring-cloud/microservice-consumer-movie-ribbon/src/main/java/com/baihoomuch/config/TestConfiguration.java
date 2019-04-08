package com.baihoomuch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 
 * CustomConfiguration CLA必须是一个@Configuration类，但是要注意，对于主应用程序上下文来说
 * ，它不是在@ComponentScan中。否则，所有@RibbonClients都会共享它。如果使用@ComponentScan
 * (或@SpringBootApplication)，则需要采取步骤避免包含它(例如，您可以将其放在单独的、不重叠的包中，
 * 或者指定要在@ComponentScan中显式扫描的包)。
 * 
 * 
 * @author Administrator
 *		自定义ribbon 负载均衡（指的是开启多个线程，cpu合理的调度）
 */
@Configuration
public class TestConfiguration {
	
	@Autowired
	IClientConfig iClientConfig;
	
	@Bean
	public IRule ribbonRule(IClientConfig iClientConfig) {
		//消费端微服务，负载均衡最佳高可用规则
		//return new BestAvailableRule();
		//消费端微服务, 随机可用规则
		return new RandomRule();
	}

}
