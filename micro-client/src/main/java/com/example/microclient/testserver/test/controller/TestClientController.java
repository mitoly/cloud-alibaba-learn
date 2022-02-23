package com.example.microclient.testserver.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.microclient.testserver.test.service.TestClientService;
import com.example.microclient.testserver.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test-client")
@RefreshScope // Nacos配置实时更新
public class TestClientController {

    @Autowired
    private TestClientService testClientService;

    @Value("${propertiesStr:aaa}")
    private String propertiesStr;

    @GetMapping("/hello/{hello}")
    public String testServerHello(@PathVariable String hello) {
        String msg = testClientService.testServerHello(hello);
        return msg;
    }

    @GetMapping("/get-properties")
    @SentinelResource(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public String getProperties() {
        return propertiesStr + "cccgo";
    }


}
