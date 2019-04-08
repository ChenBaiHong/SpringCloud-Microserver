package com.baihoomuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
/**
 * 
 * @author Administrator
 */
@SpringBootApplication
@EnableHystrixDashboard // 能够启用Hystrix仪表板
public class MicroserviceHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHystrixDashboardApplication.class, args);
    }
}
