package com.example.demo.thread.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁及解除死锁  main和BThead陷入了无限期的等待,似乎停止了
 */
public class DeadLock {
    private static ReentrantLock Alock = new ReentrantLock();
    private static ReentrantLock Block = new ReentrantLock();


   static class BThead implements Runnable{

        @Override
        public void run() {
            Alock.lock();
            System.out.println(Thread.currentThread().getName()+"获取了A锁");
            Block.lock();
            Alock.unlock();
            Block.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new BThead());
        t.setName("B线程");
        t.start();
        Block.lock();
        System.out.println(Thread.currentThread().getName()+"获取了B锁");
        //为了B线程先执行获取锁的操作,休眠main线程5s
        TimeUnit.SECONDS.sleep(5);
        Alock.lock();
        Block.unlock();
        Alock.unlock();
    }

}
