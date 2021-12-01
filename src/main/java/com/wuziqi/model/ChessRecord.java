package com.wuziqi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ChessRecord {
    @TableId(type = IdType.AUTO)
    private int id;
    private int gameId;
    private int x;
    private int y;
    private String color;
    private String createTime;

}
