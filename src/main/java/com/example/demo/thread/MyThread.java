package com.example.demo.thread;

import java.util.concurrent.ConcurrentHashMap;

public class MyThread implements Runnable{

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1);

    @Override
    public void run() {
        try {
           concurrentHashMap.put(3,"test3");
            System.out.println(Thread.currentThread().getName()+"插入了一个元素");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new MyThread());
//        thread.start();
//        thread.interrupt();
//        Thread.sleep(1000);//sleep main从运行态变为阻塞态，MyThread获得时间片，就绪态变为运行态，打印"MyThead run"
//        System.out.println("main run");

        //模拟一个线程插入后正在扩容，另一个线程put会发生什么？

        concurrentHashMap.put(1,"test");//主线程插入
        concurrentHashMap.put(4,"test4");
        Thread thread = new Thread(new MyThread(),"myThraed");
        thread.start();
        concurrentHashMap.put(2,"test2");

    }
}
