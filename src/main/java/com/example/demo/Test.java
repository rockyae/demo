package com.example.demo;

public class Test {
    public static int test1() {
        try {
            System.out.println("nihao");
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            System.out.println("Finally executed");
            return 3;
        }
//        System.out.println("hellp world");

    }

    public static void main(String[] args) {
        System.out.println(test1());
    }
}