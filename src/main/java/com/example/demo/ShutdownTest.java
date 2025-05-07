package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutdownTest {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 提交任务
        executorService.submit(() -> {
            try {
                System.out.println("Task 1 is running.");
                Thread.sleep(2000);
                System.out.println("Task 1 is completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println("Task 2 is running.");
                Thread.sleep(1000);
                System.out.println("Task 2 is completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        // 调用 shutdown() 方法
        executorService.shutdown();
        // 此时再提交任务会抛出 RejectedExecutionException
        try {
            executorService.submit(() -> System.out.println("This task will be rejected."));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        //生成链表，打印链表
        Integer[] arr = {1,2,3,4,5};
        Node<Integer> head = createList(arr);
        printList(head);
    }

    //生成printList打印链表，所有元素之间用箭头连接，只打印一行，打印最后一个元素换行
    public static <T> void printList(Node<T> head){
        while(head!=null){
            System.out.print(head.data);
            if(head.next!=null){
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }
     





    static class Node<T>{
        T data;
        Node<T> next;
        public Node(T data){
            this.data = data;
        }
    }
    //修改成尾插法生成链表，名字要改
    public static <T> Node<T> createList(T[] arr){
        Node<T> head = null;
        for (T t : arr) {
            Node<T> node = new Node<>(t);
            node.next = head;
            head = node;
        }
        return head;
    }

}
