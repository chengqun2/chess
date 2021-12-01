package com.wuziqi.controller;

import com.wuziqi.model.ChessRecord;
import com.wuziqi.service.WuziqiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/games")
public class GameController {


    @Resource
    private WuziqiService wuziqiService;

    @GetMapping(value = "")
    public Map<String,Object> games(){
        return wuziqiService.getGames();
    }

    @PostMapping(value = "")
    public Map<String,Integer> createGame(){
        return wuziqiService.createGame();
    }

    @GetMapping(value = "/{gameId}")
    public Map<String,Object> getDetail(@PathVariable int gameId){
        return wuziqiService.getDetail(gameId);
    }


    @PostMapping(value = "/{gameId}/positions")
    public Map<String,Object> positions(@PathVariable int gameId,@RequestBody ChessRecord record){
        record.setGameId(gameId);
        return wuziqiService.checkCompleteStatus(record);
    }
}
