package com.example.demo.proxy;

public class MyObject implements PrintNum{

    private int num;

    MyObject(int num){
        this.num = num;
    }


    public void print(){
        System.out.println(this.num);
    }

    @Override
    public void play() {
        System.out.println("play");
    }
}
