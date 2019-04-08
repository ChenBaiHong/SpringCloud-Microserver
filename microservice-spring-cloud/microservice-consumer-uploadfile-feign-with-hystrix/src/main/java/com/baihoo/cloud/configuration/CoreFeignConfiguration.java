package com.baihoo.cloud.configuration;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

/**
 * 	配置springcloud使用feign实现服务间调用，参数数据太大，无法调用成功解决之法
 * @author Administrator
 *
 */
@Configuration
public class CoreFeignConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(this.messageConverters));
    }
    @Bean
    public Logger.Level logger() {
        return Logger.Level.FULL;
    }
}