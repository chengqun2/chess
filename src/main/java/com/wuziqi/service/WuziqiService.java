package com.wuziqi.service;

import com.wuziqi.model.ChessRecord;

import java.util.Map;

public interface WuziqiService{
    Map checkCompleteStatus(ChessRecord point);

    Map<String,Object> getGames();

    Map<String,Object> getDetail(int gameId);

    Map<String,Integer> createGame();
}

