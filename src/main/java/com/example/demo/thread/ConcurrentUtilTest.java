package com.example.demo.thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ConcurrentUtilTest {



    static class CountDownLatchTest {
        static CountDownLatch c = new CountDownLatch(3);//传入N

        public static void main(String[] args) throws InterruptedException {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                    c.countDown();//N-1
                    System.out.println(2);
                    c.countDown();
                }
            }).start();

            c.await();//阻塞当前线程，知道N=0
            System.out.println("3");
        }
    }

    static class CyclicBarrierTest {
        static CyclicBarrier c = new CyclicBarrier(34);

        public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        c.await();
                    } catch (Exception e){
                    }
                    System.out.println(1);
                }
            }).start();
            //main线程和无名线程一起执行
            try{
                c.await();
            } catch (Exception e){
            }
            
            System.out.println(2);
        }
    }



}
