package com.example.demo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer a = in.nextInt();
        Integer b = in.nextInt();
        int res = -1;
        for (int i = 0; i <= b; i++) {
            System.out.println(Integer.toBinaryString(i));
            int temp = i ^ a;
            res = Math.max(temp, res);
        }
        System.out.println(res);
    }
}
