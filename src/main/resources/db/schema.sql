create table USER(
    ID int auto_increment,
    USERNAME VARCHAR2,
    PASSWORD VARCHAR2,
    CREATE_TIME VARCHAR2,
    constraint USER_PK
        primary key (ID)
);

create table CHESS
(
    ID         int auto_increment,
    START_TIME VARCHAR2,
    END_TIME   VARCHAR2,
    RESULT     VARCHAR2,
    constraint CHESS_PK
        primary key (ID)
);

create table CHESS_RECORD
(
    ID         int auto_increment,
    GAME_ID    int,
	X    INT,
	Y    INT,
	COLOR VARCHAR2,
    CREATE_TIME VARCHAR2,
    constraint CHESS_RECORD_PK
        primary key (ID)
);