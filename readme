Java 五子棋AI homework
===================

使用Java实现一个简单的五子棋AI并将功能以Rest API的形式向外公开。

## 假设信息

为简单起见，此处的五子棋规则为最简化的版本，并且也采用守相关专业规则与术语。

- 假设棋盘大小为 20x20
- 每局棋开始都是由用户先落子
- 规则：某一方的棋子先在横、竖、或斜的方向上有五个棋子连成一线，则胜。如果棋盘所有位置都占满也没有达到，则为和棋。
- 落子位置表示为`{x:number,y:number}`，以左下角为原点，从`0`开始算起


## 作业需求

该作业不要求实现前端，只要求提供以下rest api接口。为了方便，我们已经提供了前端界面，可以填入我们的后端url进行操作：

https://recruitment-cn.github.io/frontend-gobang-ui-demo-for-backend/

由于存在跨域问题，所以我们实现的所有API都要支持CORS

下方的类型描述使用的是typescript类型

### 1. 开启新的一局游戏

```
POST /games
```

参数：无

返回值：
```
{ gameId: number }
```

### 2. 用户落子
```
POST /games/{gameId}/positions
```

#### 参数：
落子位置
```
{ x: number, y: number}
```
如果落子位置有误，则返回某种错误

#### 返回值
有四种情况：

1. 进行中
```
{ complete: false, aiNextPosition: {x: number, y: number} }
```

2. 用户胜
```
{ complete: true, winner: 'user' }
```

3. AI胜
```
{ complete: true, winner: 'AI', aiNextPosition: {x: number, y: number} }
```

4. 和局（无落子位置）
```
{ complete: true, winner: 'none' }
```

### 3. 棋局列表

```
GET /games
```

参数：无

返回值：所有棋局gameId，以及完成情况
```
{
  games: {
    id: number,
    complete: boolean,
    winner: string
  }[]
}
```

### 4. 某一局详情

```
GET /games/{gameId}
```

参数: 无

返回值：该棋局落子历史信息，以及完成情况
```
{
  gameId: number,
  positions: {from: 'user|AI', position: {x: number, y: number}}[],
  complete: boolean,
  winner: string
}
```

## AI策略

按顺序使用以下规则实现一个简单的AI：
1. 如果发现自己下子后可以获胜，则在对应位置落子
1. 否则，当发现用户的棋有四个连在一起的，需要去落子堵住一头
1. 否则，当发现用户的棋有三个连在一起的，需要去落子堵住一头
2. 否则，在合适位置落子尽量去构建一个最长的连子

注意：我们并不是想要实现一种AI必胜策略，而是希望能按照指定需求实现功能。其中有一些不确定的地方可以自由发挥

## 技术要求

1. 提供一条命令进行server的初始化、启动等功能，提示可访问的 api base url
2. Java使用任意框架，实现Restful接口
3. 使用数据库（关系数据库或NoSQL），自行建表，使用SQL/NO-SQL/ORM等。为了Review方便，推荐使用较简单的文件式数据库（如sqlite/h2等），不需要安装
4. 有恰当的单元测试（非常重要）
5. Clean code
5. 作业以PR形式提交必要的代码和文件, 注意不要提交压缩包

