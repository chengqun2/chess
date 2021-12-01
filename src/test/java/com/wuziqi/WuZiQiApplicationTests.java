package com.wuziqi;

import com.wuziqi.model.ChessRecord;
import com.wuziqi.service.WuziqiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class WuZiQiApplicationTests {

    @Resource
    private WuziqiService wuziqiService;

    @Test
    void testGames() {
        System.out.println(wuziqiService.getGames());
    }

    @Test
    void createGame() {
        System.out.println(wuziqiService.createGame());
    }

    @Test
    public void positions(){
        Map<String,Integer> map = wuziqiService.createGame();
        int gameId = map.get("gameId");
        ChessRecord record = new ChessRecord();
        record.setX(10);
        record.setY(10);
        record.setGameId(gameId);
        System.out.println(wuziqiService.checkCompleteStatus(record));
        System.out.println(wuziqiService.getDetail(gameId));
    }

}
