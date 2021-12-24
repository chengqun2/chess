package com.wuziqi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test_get_from_redis(){
        Map map = new HashMap();
        map.put("code","000");
        redisTemplate.opsForValue().set("1", map);
        System.out.println(redisTemplate.opsForValue().get("1"));
    }
}
