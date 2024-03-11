package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;
    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> threadList = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        //新建100个线程，每个线程将i单次加1，加10000次
        for(int j=0; j<100; j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            threadList.add(t);
        }
        //同时启动100个线程
        for(Thread t: threadList){
            t.start();
        }
        //等待所有线程执行完成
        for(Thread t: threadList){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);//984852
        System.out.println(cas.atomicI.get()); //1000000
        System.out.println(System.currentTimeMillis() - start); //123ms
    }

    private void count(){
        i++;
    }
    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            //加一次就返回，不能加就重试
            if(suc){
                break;
            }
        }
    }
}
