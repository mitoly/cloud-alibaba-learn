package com.example.microclient.testserver.test.service.impl;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.microclient.testserver.test.service.TestClientService;
import com.example.microclient.testserver.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestClientServiceImpl implements TestClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    /*defaultFallback为熔断方法，必须在同一个类中，如果希望指定其它类则用fallbackClass
    * 具体的流控配置需要见Sentinel控制台*/
    @Override
    @SentinelResource(value = "testServerHello", defaultFallback = "testFallBack", blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public String testServerHello(String hello) {
//        String str = "1";
//        str.charAt(6);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "micro-client："+ port + "：" + restTemplate.getForObject("http://micro-server/test-server/hello/"+hello, String.class);
//        Entry entry = null;
//        try {
//            // 定义一个sentinel保护的资源，名称为test-sentinel-api
//            entry = SphU.entry("testServerHello");
//            // 模拟执行被保护的业务逻辑耗时
//            Thread.sleep(5000);
//            return "micro-client："+ port + "：" + restTemplate.getForObject("http://micro-server/test-server/hello/"+hello, String.class);
//        } catch (BlockException e) {
//            // 如果被保护的资源被限流或者降级了，就会抛出BlockException
//            e.printStackTrace();
//            return "资源被限流或降级了";
//        } catch (InterruptedException e) {
//            return "发生InterruptedException";
//        } finally {
//            if (entry != null) {
//                entry.exit();
//            }
//            ContextUtil.exit();
//        }
    }

    /*熔断方法参数可以为空或 Throwable来接受异常*/
    public String testFallBack(Throwable e) {
        return "testFallBack：" + e.getMessage();
    }
}
