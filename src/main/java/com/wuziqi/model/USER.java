package com.wuziqi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("USER")
public class USER {
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private String password;
    private String createTime;

}
