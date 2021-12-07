# Java 五子棋人机对战 的后台部分(Rest API)
```
SpringBoot、Https、跨域请求、h2数据库（免安装）
```
1、棋盘大小为 20x20
```
2、用户先落子（机器后下）
```
3、落子位置表示为`{x:number,y:number}`，以左下角为原点，从`0`开始算起
```
4、实现如下接口：
```
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
2. 否则，当发现用户的棋有四个连在一起的，需要去落子堵住一头
3. 否则，当发现用户的棋有三个连在一起的，需要去落子堵住一头
4. 否则，在合适位置落子尽量去构建一个最长的连子
