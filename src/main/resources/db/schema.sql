create table CHESS
(
    ID         int auto_increment,
    START_TIME VARCHAR(20),
    END_TIME   VARCHAR(20),
    RESULT     VARCHAR(20),
    constraint CHESS_PK
        primary key (ID)
);

create table CHESS_RECORD
(
    ID         int auto_increment,
    GAME_ID    int,
	X    INT,
	Y    INT,
	COLOR VARCHAR(20),
    CREATE_TIME VARCHAR(20),
    constraint CHESS_RECORD_PK
        primary key (ID)
);