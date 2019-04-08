package com.baihoomuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.baihoomuch.cloud.vo.UserVo;

/**
 * Description: 
 * 		TODO 该工程应用名称 microservice-simple-consumer-movie
 * auther 
 * 		Administrator on 2018/7/6
 */
@RestController
@RequestMapping("/contro")

public class controller {
    /**
     * RestTemplate 是访问远程微服务spring提供的模板对象，注意这里要自动注入restTemplate对象时必须在应用服务启动层new出该对象
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过 application.yml 配置文件配置 微服务远程路径
     */
    @Value("${user.msremotepath}")
    private String msremotepath;
    /**
     * RESTFul 格式例：http://127.0.0.1:7901/msconsumer/contro/findUser/1
     * @PathVariable 指的就是使用后面RESTFul传递的参数
     * @param objectid 通过占位名称（占位名称得与形参名称一致）直接在访问的Url后面带参数值 例如：
     * @return
     */
    @GetMapping("/movie/{objectid}")
    public UserVo findUserVo(@PathVariable Long objectid){
        return restTemplate.getForObject(this.msremotepath+objectid,UserVo.class);
    }
}
