package com.example.microclient.testserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-server")
public class TestServerController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hello/{hello}")
    public String testServerHello(@PathVariable String hello) {
        return "SUCCESS：" + port + "：" + hello;
    }


}
