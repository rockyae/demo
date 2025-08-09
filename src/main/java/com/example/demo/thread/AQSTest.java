package com.example.demo.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {


    private  static ReentrantLock lock = new ReentrantLock(true);


    public static void main(String[] args) {
        //创建3个并发线程，模拟只有一个获取到锁,持有2s后释放，另一个被阻塞
        for(int i=0; i< 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+" lock...");
                    System.out.println(Thread.currentThread().getName()+" do something...");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName()+" unlock...");
                    }
                }
            },"thread--"+i).start();
        }
    }
}
