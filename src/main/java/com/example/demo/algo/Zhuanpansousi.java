package com.example.demo.algo;

import java.util.Arrays;
import java.util.Scanner;

public class Zhuanpansousi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int N = str.length;
        int[] prices = new int[N];
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(str[i]);
        }

        for (int i = 0; i < N; i++) {
            int sum = prices[i];
            int j;
            if(i == N-1){
                j = 0;
            }else{
                j = i+1;
            }
            //没遍历到i的位置
            while (i != j){
                if(prices[j] < prices[i]){
                    sum += prices[j];
                    break;
                }else{
                    if(j == N-1){
                        j = 0;
                    }else{
                        j++;
                    }
                }
            }
            res[i] = sum;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb.substring(0,sb.length()-1).toString());
    }
}
