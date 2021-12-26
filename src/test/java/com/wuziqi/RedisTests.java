package com.wuziqi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test_get_from_redis(){
        redisTemplate.opsForValue().set("1", "a");
        log.info("String:"+redisTemplate.opsForValue().get("1"));
    }
}
