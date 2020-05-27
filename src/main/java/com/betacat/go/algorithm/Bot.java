package com.betacat.go.algorithm;

import com.betacat.go.domain.Data;
import com.betacat.go.domain.Point;
import com.betacat.go.config.Global;
import com.betacat.go.utils.Rule;

import java.util.Random;

public class Bot {
    private int [][] board;
    public Data handle(int[][] board){
        this.board=board;
        Data data=new Data();
        Point p=null;
        int result = Rule.whoWin(board);
        if(result==1||result==0){
            data.setResult(result);
            return data;
        }

        if(Global.TURN==1){
            p=handleTheFirstTurn();
        }else
            p=generalHandle();

        board[p.getY()][p.getX()]=0;
        result=Rule.whoWin(board);
        data.setP(p);
        data.setResult(result);

        System.out.println(data);
        System.out.println("----------------------------");
        Global.TURN++;
        return data;
    }

    //处理第一轮
    Point handleTheFirstTurn(){
        boolean flag=true;
        Point myPoint=null;

        //在指定范围中寻找
        for(int i=5;i<10;i++){
            if(flag==false)
                break;
            for(int j=5;j<10;j++){
                if(board[i][j]==1){
                    System.out.println("player:("+i+","+j+")");
                    myPoint=nextRandomPoint(i,j);
                    flag=false;
                    break;
                }
            }
        }
        //没找到中心区域随机落子
        if(flag){
            myPoint=nextRandomPoint(7,7);
        }

        return myPoint;
    }

    //第一轮的随机落子
    Point nextRandomPoint(int y,int x){
        Point p=null;
        Random r=new Random();
        int ran=r.nextInt(8);
        switch (ran){
            case 0:p=new Point(y+1,x+1);break;
            case 1:p=new Point(y+1,x-1);break;
            case 2:p=new Point(y-1,x-1);break;
            case 3:p=new Point(y-1,x+1);break;
            case 4:p=new Point(y,x+1);break;
            case 5:p=new Point(y,x-1);break;
            case 6:p=new Point(y+1,x);break;
            case 7:p=new Point(y-1,x);break;
        }
        return p;
    }

    //第一轮后的通用处理
    Point generalHandle(){
        int maxValue=0;
        Point bestPoint=null;
        for(int i=0;i<Rule.HEIGHT;i++)
            for(int j=0;j<Rule.WIDTH;j++){
                if(board[i][j]==-1){
                    Point p=new Point(i,j);
                    int value=evaluatePoint(p,0,1);
                    if(value>maxValue){
                        maxValue=value;
                        bestPoint=p;
                    }
                }
            }
        System.out.println("最大值:"+maxValue);
        System.out.println(bestPoint);
        return bestPoint;
    }

    /**
     * 获取一点
     * @param p 当前点
     * @param dir 方向
     * @param t  相对偏移距离
     * @return
     */
    int getPoint(Point p,int dir,int t){
        int x=p.getX(),y=p.getY();
        switch (dir){
            case 1:
                x+=t;
                break;
            case 2:
                x+=t;
                y+=t;
                break;
            case 3:
                y+=t;
                break;
            case 4:
                x-=t;
                y+=t;
                break;
            case 5:
                x-=t;
                break;
            case 6:
                x-=t;
                y-=t;
                break;
            case 7:
                y-=t;
                break;
            case 8:
                x+=t;
                y-=t;
        }
        //-2 非法;-1 空;0 白;1 黑
        if(!Rule.isLegal(y,x))
            return -2;

        return board[y][x];
    }

    /**
     * 估值函数
     * @param p
     * @param m 我
     * @param e 对手
     * @return
     */
    int evaluatePoint(Point p,int m,int e){
        int value=0;
        //八个方向
        for(int i=1;i<=8;i++){
            //x 其它空位置
            //+ 我
            //- 对手
            //* 当前空位置

            //活四 x++++*
            if (getPoint(p,i,-1) == m && getPoint(p, i, -2) == m
                && getPoint(p, i, -3) == m && getPoint(p, i, -4) == m
                && getPoint(p, i, -5) == -1) {
                System.out.println("活四");
                value += 300000;
                continue;
            }

            // 死四A -++++*
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, -3) == m && getPoint(p, i, -4) == m
                    && (getPoint(p, i, -5) == e )) {
                System.out.println("死四A");
                value += 250000;
                continue;
            }

            // 死四B +++*+
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, -3) == m && getPoint(p, i, 1) == m) {
                System.out.println("死四B");
                value += 240000;
                continue;
            }

            // 死四C ++*++
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, 1) == m && getPoint(p, i, 2) == m) {
                System.out.println("死四C");
                value += 230000;
                continue;
            }

            //活三 近3位置 +++*x
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, -3) == m) {
                System.out.println("近活三");
                if (getPoint(p, i, 1) == -1) {
                    value += 750;
                }
                if (getPoint(p, i, 1) == e) {
                    value += 500;
                }
                continue;
            }

            // 活三 远3位置 +++x*
            if (getPoint(p, i, -1) == -1 && getPoint(p, i, -2) == m
                    && getPoint(p, i, -3) == m && getPoint(p, i, -4) == m) {
                System.out.println("远活三");
                value += 350;
                continue;
            }

            // 死三 ++*+
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, 1) == m) {
                System.out.println("死三");
                value += 600;
                //边缘都为空
                if (getPoint(p, i, -3) == -1 && getPoint(p, i, 2) == -1) {
                    value += 3150;
                    continue;
                }
                //边缘皆为敌人
                if (getPoint(p, i, -3) == e && getPoint(p, i, 2) == e) {
                    continue;
                } else {
                    //一空一敌人
                    value += 700;
                    continue;
                }
            }
            
            //活二
            if (getPoint(p, i, -1) == m && getPoint(p, i, -2) == m
                    && getPoint(p, i, -3) != e&&getPoint(p,i,1)!=e) {
                System.out.println("活二");
                value+=1500;
            }

            //散棋
            if(getPoint(p,i,1)==m){
                System.out.println("散棋");
                value+=100;
            }

        }
        return value;
    }
}
