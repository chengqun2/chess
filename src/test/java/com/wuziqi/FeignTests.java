package com.wuziqi;

import com.wuziqi.service.InvokeYdpcsFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class FeignTests {

    @Resource
    private InvokeYdpcsFeignClient invokeYdpcsFeignClient;

    @Test
    public void test(){
        Map map = new HashMap<>();
        map.put("username","admin");
        map.put("password","admin");
        String token = invokeYdpcsFeignClient.getToken(map).get("token").toString();
        System.out.println(token);
        System.out.println(invokeYdpcsFeignClient.test("Bearer "+token,1));
    }
}
