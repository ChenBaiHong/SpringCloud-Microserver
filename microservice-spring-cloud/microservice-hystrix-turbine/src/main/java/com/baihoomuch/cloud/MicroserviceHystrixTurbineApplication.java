package com.baihoomuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
/**
 * 
 * @author Administrator
 */
@SpringBootApplication
@EnableHystrixDashboard // 能够启用Hystrix仪表板
@EnableTurbine //启用聚合监控
public class MicroserviceHystrixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHystrixTurbineApplication.class, args);
    }
}
