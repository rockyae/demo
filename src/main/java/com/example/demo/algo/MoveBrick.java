package com.example.demo.algo;

import java.util.Arrays;
import java.util.Scanner;

public class MoveBrick {

    /**
     * N 堆砖，机器人每小时选一堆，，8小时内搬完最少需要多少能量
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] bricks = Arrays.stream(sc.nextLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        int ddl = 8;
        int max = -1;
        for (int i = 0; i < bricks.length; i++) {
            max = Math.max(max, bricks[i]);
        }
        if(bricks.length > 8){
            System.out.println(-1);
        }
        //能量区间
        int left = 1, right = max;
        //这里取等可能会死循环
        while(left < right){
            int mid = (right - left)/2 +left;
            //mid得出的时间sum 大于 ddl, 则l = m+1;
            //小于等于ddl ,这时能量有可能偏大,r=mid;
            if(calTime(bricks, mid) > ddl){
                left = mid +1;
            }else{
                right = mid;
            }
        }
        // l>=r, 两种情况
        if(calTime(bricks, left) > ddl){
            System.out.println(-1);
        }else{
            System.out.println(left);
        }


    }
    //计算搬砖总耗时
    static int calTime(int[] bricks,int speed){
        int sum = 0;
        for (int i = 0; i < bricks.length; i++) {
            if(bricks[i] % speed != 0){
                sum++;
            }
            sum += bricks[i]/speed;
        }
        return sum;
    }
}
