//package com.wuziqi.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.wuziqi.datasource.datasource1.model.Chess;
//import com.wuziqi.controller.vm.ChessPointVM;
//import com.wuziqi.datasource.datasource1.model.ChessRecord;
//import com.wuziqi.controller.vm.Color;
//import com.wuziqi.controller.vm.Result;
//import com.wuziqi.datasource.datasource1.repository.ChessMapper;
//import com.wuziqi.datasource.datasource1.repository.ChessRecordMapper;
//import com.wuziqi.util.DateUtil;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class WuziqiServiceImpl implements WuziqiService{
//
//    private int MAX_LINE = 20;
//
//    private ChessPointVM aiNextPosition;
//
//    //已下的黑棋的列表
//    private List<ChessPointVM> blackChessPointVMList = new ArrayList<>();
//
//    //已下的白棋的列表
//    private List<ChessPointVM> whiteChessPointVMList = new ArrayList<>();
//
//    //剩下的空位
//    private List<ChessPointVM> restOfChessPointVMList = new ArrayList<>();
//
//    @Resource
//    private ChessMapper chessMapper;
//
//    @Resource
//    private ChessRecordMapper chessRecordMapper;
//
//    @Override
//    public Map checkCompleteStatus(ChessRecord record) {
//        record.setColor(Color.BLACK.toString());
//        Map map = new HashMap();
//        if (record.getX()>20 || record.getX() <0 || record.getY()>20 || record.getY()<0){
//            map.put("reslut","error");
//            map.put("reslutInfo","超出棋盘边界!");
//            return map;
//        }
//        QueryWrapper<ChessRecord> chessRecordQueryWrapper = new QueryWrapper<>();
//        chessRecordQueryWrapper.select().eq("game_id",record.getGameId())
//            .eq("x",record.getX()).eq("y",record.getY());
//        List<ChessRecord> chessRecordList = chessRecordMapper.selectList(chessRecordQueryWrapper);
//        if (chessRecordList!=null && chessRecordList.size()>0){
//            map.put("reslut","error");
//            map.put("reslutInfo","该位置已经有棋子!");
//            return map;
//        }
//
//        Chess chess = new Chess();
//        chess.setId(record.getGameId());
////        record.setId(snowFlake.nextId()+"");
//        record.setCreateTime(DateUtil.getCurrentTimeStamp());
//        chessRecordMapper.insert(record);
//        QueryWrapper<ChessRecord> blackPointQueryWrapper = new QueryWrapper<>();
//        blackPointQueryWrapper.select("x","y").eq("game_id",record.getGameId())
//                .eq("color","BLACK");
//        List<ChessRecord> blackChessRecordList = chessRecordMapper.selectList(blackPointQueryWrapper);
//        blackChessPointVMList = convertToChessPointList(blackChessRecordList);
//        boolean blackWin = checkPointsInLine(blackChessPointVMList,5);
//        //如果游戏结束,获取游戏结果mGameWinResult
//        if (blackWin) {
//            chess.setEndTime(DateUtil.getCurrentTimeStamp());
//            chess.setResult(Result.user.toString());
//            chessMapper.updateById(chess);
//            map.put("complete",true);
//            map.put("winner",Result.user);
//            blackChessPointVMList = new ArrayList<>();
//            whiteChessPointVMList = new ArrayList<>();
//            return map;
//        }else{
//            aiNextPosition = getAINextPosition(record);
//            record.setX(aiNextPosition.getX());
//            record.setY(aiNextPosition.getY());
//            record.setColor(Color.WHITE.toString());
//            record.setCreateTime(DateUtil.getCurrentTimeStamp());
//            chessRecordMapper.insert(record);
//            QueryWrapper<ChessRecord> whitePointQueryWrapper = new QueryWrapper<>();
//            whitePointQueryWrapper.select("x","y").eq("game_id",record.getGameId())
//                    .eq("color","WHITE");
//            List<ChessRecord> whiteChessRecordList = chessRecordMapper.selectList(whitePointQueryWrapper);
//            whiteChessPointVMList = convertToChessPointList(whiteChessRecordList);
//            boolean whiteWin = checkPointsInLine(whiteChessPointVMList,5);
//            boolean noWin = checkNoWin(whiteWin,blackWin);
//            if (whiteWin) {
//                chess.setEndTime(DateUtil.getCurrentTimeStamp());
//                chess.setResult(Result.AI.toString());
//                chessMapper.updateById(chess);
//                map.put("complete",true);
//                map.put("aiNextPosition",aiNextPosition);
//                map.put("winner", Result.AI);
//                blackChessPointVMList = new ArrayList<>();
//                whiteChessPointVMList = new ArrayList<>();
//                return map;
//            } else if(noWin){
//                chess.setEndTime(DateUtil.getCurrentTimeStamp());
//                chess.setResult(Result.none.toString());
//                chessMapper.updateById(chess);
//                map.put("complete",true);
//                map.put("winner",Result.none);
//                blackChessPointVMList = new ArrayList<>();
//                whiteChessPointVMList = new ArrayList<>();
//                return map;
//            }else{
//                map.put("complete",false);
//                map.put("aiNextPosition",aiNextPosition);
//                return map;
//            }
//        }
//    }
//
//    @Override
//    public Map<String,Object> getGames() {
//        Map map = new HashMap();
//        List<Chess> list = chessMapper.selectList(new QueryWrapper<Chess>().orderByAsc("start_time"));
//        List<Map> chessMaps = new ArrayList<>();
//        for (Chess chess : list) {
//            Map chessMap = new HashMap();
//            chessMap.put("id",chess.getId());
//            if (StringUtils.isNotEmpty(chess.getEndTime())){
//                chessMap.put("complete",true);
//            }else{
//                chessMap.put("complete",false);
//            }
//            chessMap.put("winner",chess.getResult());
//            chessMaps.add(chessMap);
//        }
//        map.put("games",chessMaps);
//        return map;
//    }
//
//    @Override
//    public Map<String,Object> getDetail(int gameId) {
//        Map map = new HashMap();
//        Chess chess = chessMapper.selectList(new QueryWrapper<Chess>().eq("id",gameId)).get(0);
//        map.put("gameId",gameId);
//        if (StringUtils.isNotEmpty(chess.getEndTime())){
//            map.put("complete",true);
//        }else{
//            map.put("complete",false);
//        }
//        map.put("winner",chess.getResult());
////        positions: {from: 'user|AI', position: {x: number, y: number}}[],
//        List<ChessRecord> positions = chessRecordMapper.selectList(new QueryWrapper<ChessRecord>().eq("game_id",gameId));
//        List<Map> positionMaps = new ArrayList<>();
//        for (ChessRecord position : positions) {
//            Map positionMap = new HashMap();
//            if (Color.BLACK.toString().equals(position.getColor())){
//                positionMap.put("from","user");
//            }else {
//                positionMap.put("from","AI");
//            }
//            Map positionMap2 = new HashMap();
//            positionMap2.put("x",position.getX());
//            positionMap2.put("y",position.getY());
//            positionMap.put("position",positionMap2);
//            positionMaps.add(positionMap);
//        }
//        map.put("positions",positionMaps);
//        return map;
//    }
//
//    @Override
//    public Map<String, Integer> createGame() {
//        Map map = new HashMap();
//        Chess chess = new Chess();
//        chess.setStartTime(DateUtil.getCurrentTimeStamp());
//        chess.setEndTime("");
//        chess.setResult("");
//        chessMapper.insert(chess);
//        map.put("gameId",chess.getId());
//        return map;
//    }
//
//    private ChessPointVM getAINextPosition(ChessRecord record) {
//        //棋盘可以放入棋子的最大数
//        List<ChessPointVM> allList = new ArrayList<>();
//        for (int x = 0; x < MAX_LINE; x++) {
//            for (int y = 0; y < MAX_LINE; y++) {
//                allList.add(new ChessPointVM(x,y));
//            }
//        }
//        allList.removeAll(blackChessPointVMList);
//        allList.removeAll(whiteChessPointVMList);
//        restOfChessPointVMList = allList;
//        ChessPointVM pointVM = getAiNextPosition(restOfChessPointVMList, blackChessPointVMList, whiteChessPointVMList);
//        return pointVM;
//    }
//
//    private ChessPointVM getAiNextPosition(List<ChessPointVM> restOfPieceList, List<ChessPointVM> blackChessPointVMList, List<ChessPointVM> whiteChessPointVMList) {
//        for (ChessPointVM chessPointVM : restOfPieceList) {
//            //如果发现AI(whiteChess)下子后可以获胜，则在对应位置落子
//            whiteChessPointVMList.add(chessPointVM);
//            boolean isWhiteWin = checkPointsInLine(whiteChessPointVMList,5);
//            if (isWhiteWin){
//                return chessPointVM;
//            }else {
//                whiteChessPointVMList.remove(chessPointVM);
//            }
//        }
//        for (ChessPointVM chessPointVM : restOfPieceList) {
//            //否则，当发现用户(blackChess)的棋有四个连在一起的，需要去落子堵住一头
//            blackChessPointVMList.add(chessPointVM);
//            boolean isBlackGetFour = checkPointsInLine(blackChessPointVMList,5);
//            if (isBlackGetFour){
//                return chessPointVM;
//            }else {
//                blackChessPointVMList.remove(chessPointVM);
//            }
//        }
//        for (ChessPointVM chessPointVM : restOfPieceList) {
//            whiteChessPointVMList.add(chessPointVM);
//            boolean isWhiteGetFour = checkNewPointInLine(chessPointVM,whiteChessPointVMList,4);
//            if (isWhiteGetFour){
//                return chessPointVM;
//            }else {
//                whiteChessPointVMList.remove(chessPointVM);
//            }
//        }
//        for (ChessPointVM newChessPoint : restOfPieceList) {
//            blackChessPointVMList.add(newChessPoint);
//            //否则，当发现用户的棋有三个连在一起的，需要去落子堵住一头
//            boolean isBlackGetThree = checkNewPointInLine(newChessPoint,blackChessPointVMList,4);
//            if (isBlackGetThree){
//                return newChessPoint;
//            }else {
//                blackChessPointVMList.remove(newChessPoint);
//            }
//        }
//        for (ChessPointVM chessPointVM : restOfPieceList) {
//            whiteChessPointVMList.add(chessPointVM);
//            boolean isWhiteGetThree = checkNewPointInLine(chessPointVM,whiteChessPointVMList,3);
//            if (isWhiteGetThree){
//                return chessPointVM;
//            }else {
//                whiteChessPointVMList.remove(chessPointVM);
//            }
//        }
//        for (ChessPointVM chessPointVM : restOfPieceList) {
//            whiteChessPointVMList.add(chessPointVM);
//            boolean isWhiteGetTwo = checkNewPointInLine(chessPointVM,whiteChessPointVMList,2);
//            if (isWhiteGetTwo){
//                return chessPointVM;
//            }else {
//                whiteChessPointVMList.remove(chessPointVM);
//            }
//        }
//        int maxX = 0 , maxY = 0;
//        for (ChessPointVM pointVM : restOfPieceList) {
//            if (pointVM.getX() >= maxX){
//                maxX = pointVM.getX();
//            }
//            if (pointVM.getY() >= maxY){
//                maxY = pointVM.getY();
//            }
//        }
//        for (int i = 0; i < restOfPieceList.size(); i++) {
//            ChessPointVM chessPointVM = new ChessPointVM((maxX+i)/2,(maxY+i)/2);
//            if (restOfPieceList.contains(chessPointVM)){
//                return chessPointVM;
//            }
//        }
//        return null;
//    }
//
//    private List<ChessPointVM> convertToChessPointList(List<ChessRecord> chessRecordList) {
//        List<ChessPointVM> list = new ArrayList<ChessPointVM>();
//        for (ChessRecord chessRecord : chessRecordList) {
//            ChessPointVM point = new ChessPointVM();
//            point.setX(chessRecord.getX());
//            point.setY(chessRecord.getY());
//            list.add(point);
//        }
//        return list;
//    }
//
//    private boolean checkPointsInLine(List<ChessPointVM> points,int maxPointsInLine) {
//        for (ChessPointVM point : points) {
//            int x = point.getX();
//            int y = point.getY();
//
//            boolean checkHorizontal = checkHorizontalPointsInLine(x,y,points,maxPointsInLine);
//            boolean checkVertical = checkVerticalPointsInLine(x,y,points,maxPointsInLine);
//            boolean checkLeftDiagonal = checkLeftDiagonalPointsInLine(x,y,points,maxPointsInLine);
//            boolean checkRightDiagonal = checkRightDiagonalPointsInLine(x,y,points,maxPointsInLine);
//            if (checkHorizontal || checkVertical || checkLeftDiagonal || checkRightDiagonal) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean checkNewPointInLine(ChessPointVM newPoint,List<ChessPointVM> points,int maxPointsInLine) {
//        int x = newPoint.getX();
//        int y = newPoint.getY();
//
//        boolean checkHorizontal = checkHorizontalPointsInLine(x,y,points,maxPointsInLine);
//        boolean checkVertical = checkVerticalPointsInLine(x,y,points,maxPointsInLine);
//        boolean checkLeftDiagonal = checkLeftDiagonalPointsInLine(x,y,points,maxPointsInLine);
//        boolean checkRightDiagonal = checkRightDiagonalPointsInLine(x,y,points,maxPointsInLine);
//        if (checkHorizontal || checkVertical || checkLeftDiagonal || checkRightDiagonal) {
//            return true;
//        }
//        return false;
//    }
//
//
//
//    /**
//     * 检查横线
//     * @param x
//     * @param y
//     * @param points
//     * @param maxPointsInLine
//     * @return
//     */
//    private boolean checkHorizontalPointsInLine(int x, int y, List<ChessPointVM> points, int maxPointsInLine) {
//        int count = 1;
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x - i, y))) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x + i, y))) {
//                count++;
//            } else {
//                break;
//            }
//
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 检查向右斜的线
//     * @param x
//     * @param y
//     * @param points
//     * @param maxPointsInLine
//     * @return
//     */
//    private boolean checkRightDiagonalPointsInLine(int x, int y, List<ChessPointVM> points, int maxPointsInLine) {
//        int count = 1;
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x - i, y + i))) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x + i, y - i))) {
//                count++;
//            } else {
//                break;
//            }
//
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 检查向左斜的线
//     * @param x
//     * @param y
//     * @param points
//     * @param maxPointsInLine
//     * @return
//     */
//    private boolean checkLeftDiagonalPointsInLine(int x, int y, List<ChessPointVM> points, int maxPointsInLine) {
//        int count = 1;
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x - i, y - i))) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x + i, y + i))) {
//                count++;
//            } else {
//                break;
//            }
//
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 检查竖线
//     * @param x
//     * @param y
//     * @param points
//     * @param maxPointsInLine
//     * @return
//     */
//    private boolean checkVerticalPointsInLine(int x, int y, List<ChessPointVM> points, int maxPointsInLine) {
//        int count = 1;
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x, y + i))) {
//                count++;
//            } else {
//                break;
//            }
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        for (int i = 1;i < maxPointsInLine;i++) {
//            if (points.contains(new ChessPointVM(x, y - i))) {
//                count++;
//            } else {
//                break;
//            }
//
//        }
//        if (count == maxPointsInLine) {
//            return true;
//        }
//        return false;
//    }
//
//    //检查是否和棋
//    private boolean checkNoWin(boolean whiteWin, boolean blackWin) {
//        if (whiteWin || blackWin) {
//            return false;
//        }
//        int maxPieces = MAX_LINE * MAX_LINE;
//        //如果白棋和黑棋的总数等于棋盘格子数,说明和棋
//        if (whiteChessPointVMList.size() + blackChessPointVMList.size() == maxPieces) {
//            return true;
//        }
//        return false;
//    }
//
//}
