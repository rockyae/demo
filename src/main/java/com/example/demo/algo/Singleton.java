package com.example.demo.algo;

import com.example.demo.module.Point;

public class Singleton {
    //类的成员变量，创建实例时创建
    Point p;

    final static Point q = new Point();


    private static class innerClass{
        private static final Point INSTANCE = new Point();
    }
    public static Point getSingleInstance(){
        return innerClass.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton singleton = new Singleton();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Point p = singleton.createSinglePoint();
                System.out.println("双重锁: "+p);
                System.out.println("饿汉式: "+q);
                System.out.println("静态工厂："+getSingleInstance());
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        //只创建过一次对象
//        Singleton singleton = new Singleton();
//        Point p1 = singleton.createSinglePoint();
//        Point p2 = singleton.createSinglePoint();
//        System.out.println(p1 == p2);
    }
    /**
     *    单例模式，创建一次，得到唯一一个对象;
     *     加了同步锁可以保证不会都通过p==null的检查条件,但是效率不高,因为实际场景多次加锁没有必要
     *     public  synchronized Point createSinglePoint(){
     *             //如果实例化过直接返回
     *             if(p == null){
     *                 p = new Point();
     *             }
     *             return p;
     *     }
     */
    //懒汉式：
    public  Point createSinglePoint(){
        //初次判断如果创建过直接返回
        if(p == null){
            //避免非必要的加锁
            synchronized (Point.class){
                //再次判断是为了防止其他线程再次创建对象
                if(p == null){
                    p =  new Point();
                }
            }
        }
        return p;
    }
    //饿汉式:在类加载完成对象实例化(如果一直未使用这个对象，会造成内存的浪费)

}
