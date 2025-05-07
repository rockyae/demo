package com.example.demo.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class HotBankList {
    /**
     * 输入N,代表有N个待排序项目, 输入维度权重
     * 输入项目名和对应维度的分数
     *
     * 输出排序后的项目名列表
     * @param args
     */
    public static void main(String[] args) {
        //spring-108
        //热度降序，名字升序
        //二维字符串数组来存，一个项目对应一个字符串数组
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] weight = Arrays.stream(sc.nextLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        //1.读入第二行
        //int[] projects = new int[N];
        String[][] projects = new String[N][2];
        //得到所有项目的分数
        for (int i = 0; i < N; i++) {
            String[] s = sc.nextLine().split(" ");
            String name = s[0];
            int score = 0;
            for (int j = 0; j < 5; j++) {
                score += weight[j] * Integer.parseInt(s[j+1]);
            }
            projects[i][0] = name;
            projects[i][1] = String.valueOf(score);
        }
        Arrays.sort(projects, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                //先按热度，再按名字字母序
                int hot1 = Integer.parseInt(o1[1]);
                int hot2 = Integer.parseInt(o2[1]);
                if(hot1 != hot2){
                    return hot2 - hot1;
                }else {
                    return o1[0].toLowerCase().compareTo(o2[0].toLowerCase());
                }
            }
            @Override
            public boolean equals(Object obj){
                return true;
            }
        });
        for(String[] project : projects){
            System.out.println(project[0]);
        }
    }
}
