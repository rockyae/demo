package com.example.demo.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 演示main线程中断一个运行线程,如果运行线程处于休眠态，那么中断操作会抛出异常
 */
public class MyThread implements Runnable{

    private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1);

    @Override
    public void run() {
        try {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()){//中断位为false, 表示未被中断
                i++;
                if(i%10000 == 0)
                    System.out.println(i);
            }
            System.out.println(Thread.currentThread().isInterrupted());//中断位为true, 表示被中断
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("子线程"+Thread.currentThread().isInterrupted());//中断位为false, 表示中断位被清除
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread());
        thread.setName("MyThead");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//中断一个处于等待态的线程会抛出异常并重置中断标志位为false
        TimeUnit.SECONDS.sleep(5);
        System.out.println(thread.isInterrupted());
        System.out.println("main run");

        //模拟一个线程插入后正在扩容，另一个线程put会发生什么？
//
//        concurrentHashMap.put(1,"test");//主线程插入
//        concurrentHashMap.put(4,"test4");
//        Thread thread = new Thread(new MyThread(),"myThraed");
//        thread.start();
//        concurrentHashMap.put(2,"test2");

    }
}
