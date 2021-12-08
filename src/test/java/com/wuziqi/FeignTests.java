package com.wuziqi;

import com.wuziqi.service.InvokeYdpcsFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
public class FeignTests {

    @Resource
    private InvokeYdpcsFeignClient invokeYdpcsFeignClient;

    @Test
    public void test(){
        System.out.println(invokeYdpcsFeignClient.getTest("222").get("games"));
    }
}
