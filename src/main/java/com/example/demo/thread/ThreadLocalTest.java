package com.example.demo.thread;

public class ThreadLocalTest {
    // 创建一个 ThreadLocal 实例
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程中设置数据
        String s = "主线程初始数据";
        threadLocal.set(s);

        // 打印主线程中 ThreadLocal 的初始值
        System.out.println("主线程初始数据: " + threadLocal.get());

        // 创建子线程
        Thread childThread = new Thread(() -> {
            // 在子线程中获取数据
            String data = threadLocal.get();
            System.out.println("子线程获取到的初始数据: " + s);

            // 在子线程中修改 ThreadLocal 的数据
            threadLocal.set(s);
            System.out.println("子线程修改后的数据: " + threadLocal.get());
        });

        // 启动子线程
        childThread.start();

        try {
            // 等待子线程执行完毕
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印主线程中 ThreadLocal 的值
        System.out.println("主线程中的数据: " + threadLocal.get());

        // 清除主线程中的数据
        threadLocal.remove();
    }
}