package com.example.demo.algo;

import java.util.Scanner;

public class FindFriend {
    /**
     * 10
     * 130 135 140 125 120 115 110 145 150 105
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = scanner.nextInt();
        }
        int[] res = new int[N];

        for (int i = 0; i < N; i++) {
            boolean flag =false;
            for (int j = i+1; j < N; j++) {
                if(height[j] > height[i]){
                    res[i] = j;
                    flag = true;
                    break;
                }
            }
            if(!flag){
                res[i] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
