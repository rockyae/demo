package com.example.demo.algo;


import java.util.Scanner;

//47 01  14
public class FindPos {
    public static void main(String[] args) {
        //读入学号数组
        Scanner sc = new Scanner(System.in);
        String[] line1 = sc.nextLine().split(",");
        int[] stuNos = new int[line1.length];
        for(int i=0; i< line1.length; i++){
            stuNos[i] = Integer.parseInt(line1[i]);
        }
        Integer targetNo = Integer.parseInt(sc.nextLine());
        int i= 0;
        int j = stuNos.length-1;
        int k = 0;
        while(i <= j){
            int mid = (j-i)/2+i;
            if(stuNos[mid] > targetNo){
                j = mid-1;
            }else if(stuNos[mid] < targetNo){
                i = mid+1;
            }else{
                //中间值等于目标值
                //目标值有多个
                 k =mid;
                while(stuNos[k] == targetNo){
                    k++;
                }
                break;
            }
        }
        if( i > j){
            System.out.println(i+1);
        }else{
            System.out.println(k+1);
        }


    }
}
