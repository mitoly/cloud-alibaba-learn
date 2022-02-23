package com.example.microclient.testserver.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ExceptionUtil {

    public static ClientHttpResponse handleException(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException exception) {
        System.out.println("---------------------------ExceptionUtil-----------------------");
        try {
            execution.execute(request, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
