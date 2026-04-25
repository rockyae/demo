package com.example.demo.algo;

/**
 * @Title: PrintSeq
 * @Author rockyae
 * @Package com.example.demo.algo
 * @Date 2025/10/24 18:10
 * @description:
 */
public class PrintSeq {
    public static void main(String[] args) throws InterruptedException {
        Integer[] nums = new Integer[]{1, 2, 3};
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        int i = 0;// 计算下标和打印
        int count = 0; //活跃线程数量
        while (i < nums.length) {
            // 检查是否需要创建新线程
            if(count <= 10){
                new MyThread(nums[i++]).start();
                count++;
            }
            //System.out.println(nums[i++]);
            if(i == nums.length) {
                i = 0;
            }
            Thread.sleep(1000);
            System.out.println("在主循环中");
        }
    }

    static class MyThread extends Thread{
        private Integer num;

        public MyThread(Integer num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + num);
        }
    }
}
