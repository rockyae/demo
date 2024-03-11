package com.example.demo.proxy;

public class Client {
    public static void main(String[] args) {
    try{
        PrintNum proxy0 = (PrintNum) new MyHandler(new MyObject(1)).getProxy();
        proxy0.print();
    }catch (Exception e){
        e.printStackTrace();
    }

    }
}
