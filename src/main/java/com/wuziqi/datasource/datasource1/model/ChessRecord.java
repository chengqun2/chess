package com.wuziqi.datasource.datasource1.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "chess_record")
public class ChessRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="game_id")
    private int gameId;

    @Column(name="x")
    private int x;

    @Column(name="y")
    private int y;

    @Column(name="color")
    private String color;

    @Column(name="create_time")
    private String createTime;

}
