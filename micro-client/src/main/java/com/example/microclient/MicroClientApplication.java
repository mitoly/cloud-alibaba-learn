package com.example.microclient;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.example.microclient.testserver.util.ExceptionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    /*通过@LoadBalanced来找到注册的服务*/
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
