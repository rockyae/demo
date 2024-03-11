package com.example.demo.algo;

import java.util.Scanner;

public class Sort {
    /**
     * 题目描述
     * 著名的快速排序算法里有一个经典的划分过程：通常采用某种方法取一个元素作为主元，通过交换，把比主元小的元素放到它的左边，比主元大的元素放到它的右边。
     *
     * 给定一个划分后的正整数序列，请问有多少个元素可能是划分过程中选取的主元？ 并按升序输出这些主元。
     *
     * 解答要求
     * 时间限制：1000ms, 内存限制：256MB
     * 输入
     * 第 1 行一个正整数 N，表示正整数序列的长度，取值范围 [1, 10^5]
     * 第 2 行 N 个互不不同的正整数，每个数的取值范围[1, 10^9]
     *
     * 输出
     * 按升序排列的可能主元的列表，以单个空格分隔；或者空列表。
     *
     * 样例
     * 输入样例 1 复制
     *
     * 5
     * 1 3 2 4 5
     * 输出样例 1
     *
     * [1 4 5]
     */
    //20:49
    public static void main(String[] args) {
        int n = 5;
        int[] arr = new int[]{1,3,2,4,5};
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            int l = i-1;
            int r = i+1;
            while (l >=0 ){
                //左边数均小于主元
                if(arr[l] >= arr[i]){
                    flag = false;
                }
                l--;
            }
            while (r < arr.length){
                if(arr[r] <= arr[i]){
                    flag = false;
                }
                r++;
            }
            if(flag) {
                sb.append(arr[i] + " ");
            }
        }
        System.out.println(sb.toString());
    }

}
