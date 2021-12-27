package com.wuziqi;

import com.wuziqi.service.InvokeYdpcsFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class FeignTests {

    @Resource
    private InvokeYdpcsFeignClient invokeYdpcsFeignClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test(){
        // 将token信息放入header中
        Map map = new HashMap<>();
        map.put("username","admin");
        map.put("password","admin");
        String token = "Bearer "+invokeYdpcsFeignClient.getToken(map).get("token").toString();
        redisTemplate.opsForValue().set("admin_token",token);
        System.out.println(invokeYdpcsFeignClient.test(1));
    }
}
