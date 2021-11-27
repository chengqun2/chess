package com.wuziqi.controller.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Chess {
    @TableId(type = IdType.AUTO)
    private int id;
    private String startTime;
    private String endTime;
    private String result;

}
