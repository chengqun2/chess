# Java 五子棋人机对战 的后台部分(Rest API)
## 技术点：
    1、Spring Cloud、Spring Boot、Spring Security、Mybatis-plus
    2、Https、跨域请求、token验证
    3、h2数据库（免安装）
    4、swagger-ui     访问地址：http://localhost:8066/swagger-ui/
    5、openfeign

### 五子棋后台api介绍
    1、棋盘大小为 20x20
    2、用户先落子（机器后下）
    3、落子位置表示为`{x:number,y:number}`，以左下角为原点，从`0`开始算起
    4、先在横线、直线或斜对角线上形成5子连线者获胜
