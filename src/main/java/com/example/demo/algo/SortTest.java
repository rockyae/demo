package com.example.demo.algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SortTest {
    public static void main(String[] args) {
        //读取1,4,32,3
        //升序排序
        Scanner sc = new Scanner(System.in);
        int  N = 4;
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            arr[i] = s;
        }
        Collections.sort(Arrays.asList(arr), Comparator.comparingInt(String::length));
        System.out.println(Arrays.asList(arr));
    }
}
