package com.example.demo.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable{

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1);

    @Override
    public void run() {
        try {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()){
                i++;
                if(i%1000 == 0)
                    System.out.println(i);
            }
//            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.setName("MyThead");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//中断一个处于等待态的线程会抛出异常
        //Thread.sleep(5000);
        System.out.println(thread.isInterrupted());
        System.out.println("main run");

        //模拟一个线程插入后正在扩容，另一个线程put会发生什么？

//        concurrentHashMap.put(1,"test");//主线程插入
//        concurrentHashMap.put(4,"test4");
//        Thread thread = new Thread(new MyThread(),"myThraed");
//        thread.start();
//        concurrentHashMap.put(2,"test2");

    }
}
