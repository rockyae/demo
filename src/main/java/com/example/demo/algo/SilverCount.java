package com.example.demo.algo;


import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 */
public class SilverCount {

    public static void main(String[] args) {
        PriorityQueue<Integer> silvers = new PriorityQueue<>(Collections.reverseOrder());
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            silvers.add(sc.nextInt());
        }
        while(silvers.size() >= 3){
            int z = silvers.poll();
            int y = silvers.poll();
            int x = silvers.poll();
            if(x == y && y != z){
                silvers.add(z-y);
            }
            if(x != y && y == z){
                silvers.add(y-x);
            }
            if(x != y && y != z){
                silvers.add(Math.abs((z-y)-(y-x)));
            }
        }
        if(!silvers.isEmpty()){
            System.out.println(silvers.peek());
        }else{
            System.out.println(0);
        }
    }
}
