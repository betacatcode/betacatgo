package com.betacat.go.utils;

public class Rule {
    public static final int WIDTH=15;
    public static final int HEIGHT=15;

    //判断谁赢了 1代表黑 0代表白 -1表示谁都没赢
    public static int whoWin(int[][] map){
        for(int i=0;i<map.length;i++)
            for(int j=0;j<map[i].length;j++){
                if(map[i][j]==-1)
                    continue;
                int result=isFive(i,j,map);
                if(result==1||result==0)
                    return result;
            }
        return -1;
    }

    //判断该点是否连五子
    public static int isFive(int y,int x,int[][] map){
        int me=map[y][x];
        int sum=1,nextY,nextX;

        //左右方向
        //左
        nextX=x-1;
        while (true){
            if(isLegal(y,nextX)&&map[y][nextX]==me){
                sum++;
                nextX--;
            }
            else
                break;
        }
        //右
        nextX=x+1;
        while (true){
            if(isLegal(y,nextX)&&map[y][nextX]==me){
                nextX++;
                sum++;
            }
            else
                break;
        }
        if(sum>=5)
            return me;
        else
            sum=1;

        //上下方向
        //上
        nextY=y-1;
        while (true){
            if(isLegal(nextY,x)&&map[nextY][x]==me){
                nextY--;
                sum++;
            }
            else
                break;
        }
        //下
        nextY=y+1;
        while (true){
            if(isLegal(nextY,x)&&map[nextY][x]==me){
                nextY++;
                sum++;
            }
            else
                break;
        }
        if(sum>=5)
            return me;
        else
            sum=1;

        //左上到右下
        //左上
        nextX=x-1;
        nextY=y-1;
        while (true){
            if(isLegal(nextY,nextX)&&map[nextY][nextX]==me){
                nextX--;
                nextY--;
                sum++;
            }
            else
                break;
        }
        //右下
        nextX=x+1;
        nextY=y+1;
        while (true){
            if(isLegal(nextY,nextX)&&map[nextY][nextX]==me){
                nextX++;
                nextY++;
                sum++;
            }
            else
                break;
        }
        if(sum>=5)
            return me;
        else
            sum=1;

        //左下到右上
        //左下
        nextX=x-1;
        nextY=y+1;
        while (true){
            if(isLegal(nextY,nextX)&&map[nextY][nextX]==me){
                nextX--;
                nextY++;
                sum++;
            }
            else
                break;
        }
        //右上
        nextX=x+1;
        nextY=y-1;
        while (true){
            if(isLegal(nextY,nextX)&&map[nextY][nextX]==me){
                nextX++;
                nextY--;
                sum++;
            }
            else
                break;
        }
        if(sum>=5)
            return me;

        return -1;
    }

    public static boolean isLegal(int y,int x){
        return y>=0&&y<HEIGHT&&x>=0&&x<WIDTH;
    }
}
