package com.example.demo.jvm;

public class StackFrameDemo {
    static int a;
    public static void main(String[] args) {
        sum(1,2);
        System.out.println(a);
    }
    //方法调用实质是往虚拟机栈入栈一个sum方法栈帧;
    //方法返回实质是往虚拟机栈弹出一个sum方法栈帧;返回值就是return的值;
    public static int sum(int a, int b){
        return a+b;
    }
}
