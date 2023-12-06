package com.example.demo.algo;

import java.util.Scanner;
import java.util.Stack;

public class SortByTowStack {

    public static void main(String[] args) {
        /**
         *  两个栈实现数组排序
         *
         *
         */

        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        System.out.println();
        int[] nums = new int[len];
        int i = 0;
        while(len > 0){
            nums[i++] = sc.nextInt();
            len--;
        }
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        for(int j : nums){
            //System.out.print(j+" ");
            //如果栈空，入栈

        }
    }
}
