package com.wuziqi;

import com.wuziqi.datasource.datasource1.model.Chess;
import com.wuziqi.datasource.datasource1.repository.ChessRepository;
import com.wuziqi.datasource.datasource2.model.User;
import com.wuziqi.datasource.datasource2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestDatasource1 {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChessRepository chessRepository;

    @Test
    public void test(){
        List<Chess> list = chessRepository.findAll();
        log.info(list+"");
    }
}
