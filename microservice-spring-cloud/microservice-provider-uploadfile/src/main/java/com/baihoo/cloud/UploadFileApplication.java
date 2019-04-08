package com.baihoo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.WebApplicationInitializer;

/**
 * 
 * @author Administrator
 * @Description  启动类继承自SpringBootServletInitializer方可正常部署至常规tomcat下，其主要能够起到web.xml的作用
 */
@ServletComponentScan // 允许扫描Servlet组件（{@link WebFilter filters}，{@ link WebServlet servlets}和{@link WebListener listeners}）。 仅在使用时执行扫描嵌入式Web服务器。
@SpringBootApplication
@EnableEurekaClient
public class UploadFileApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UploadFileApplication.class, args);
    }
}
