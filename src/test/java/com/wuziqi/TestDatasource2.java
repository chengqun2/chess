package com.wuziqi;

import com.wuziqi.datasource.datasource2.model.User;
import com.wuziqi.datasource.datasource2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestDatasource2 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserRepository userRepository;

    @Test
    public void test(){
        List<User> userList = userRepository.findAll();
        log.info(userList+"");

        List<User> userList1 = userRepository.findByUsername("admin");
        log.info(userList1+"");

        List<User> userList2 = userRepository.findByUsernameAndCreateTime("admin","2021120203030311");
        log.info(userList2+"");

        List<User> userList3 = userRepository.findByUsernameOrCreateTime("admin","2021120203030311");
        log.info(userList3+"");

        User user = new User();
        user.setUsername("a");
        User a = userRepository.save(user);
        System.out.println(a.getId());

        a.setPassword("111");
        User b = userRepository.saveAndFlush(a);
        System.out.println(b.getId());
    }
}
