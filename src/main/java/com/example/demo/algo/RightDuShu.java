package com.example.demo.algo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RightDuShu {
    //读数转换
    //有正确和错误
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean flag = true;
        Set<Integer> set = new HashSet();

        for(int i=1; i<9; i++){
            set.add((int) (5*(Math.pow(10,i))));
        }
        //错误的读数
        if(num % 10 == 5 ||set.contains(num)){
            flag = false;
        }
        if(!flag){
            //xx5, 50, 500
            int j = -1;
            int temp = num;
            while(temp > 0){
                temp = temp/10;
                j++;
            }
            num -= Math.pow(10,j);
            System.out.println(num);
        }else{
            System.out.println(num);
        }
    }
}
