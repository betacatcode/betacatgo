package com.betacat.go.utils;

/**
 * @author ruin
 * @date 2020/2/22-21:33
 */
public class FormatConverter {

    public static int[][] string2array(String data){

        String[] s = data.split(",");
        int height=0,width=0;
        height=(int) Math.sqrt(s.length);
        width=height;

        int[][]a=new int [height][width];
        for(int i=0;i<height;i++)
            for (int j=0;j<width;j++)
                a[i][j]=Integer.valueOf(s[i*width+j]);

        return a;
    }
}
