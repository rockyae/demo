package com.example.demo.io;

import java.util.Objects;
import java.util.Scanner;

public class IOTest {



    //先确定转换后的最高位
    //18:14  25  37
    //测试空格，换行分隔符
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         while (sc.hasNextLine()) {

             int n = sc.nextInt();
             int r = sc.nextInt();
             if (n == -1 && r == -1) {
                 System.out.println("欢迎下次使用^_^");
                 break;
             }
             System.out.println(transferNum(n, r));
         }
//        int length = 0;
//        if (sc.hasNextLine()){
//            while  (sc.hasNextInt()){
//                int num = sc.nextInt();
//                length++;
//            }
//            //收集数组
//            int[] arr = new int[length];
//            for(int i=0; i<length; i++){
//                arr[i] = sc.nextInt();
//            }
//            System.out.println(arr);
//            //System.out.println(getCount(arr));
//            System.out.println(2);
//        }
    }
    //10进制转R进制
    public static String transferNum(int n,int r){
        if (r < 2) {
            throw new IllegalArgumentException("R must be greater than or equal to 2.");
        }
        int m = r;
        int i = 1;
        //得到10进制数n变为r进制需要的位数i
        while(m-1 < n){
            i++;
            m = r * m;
        }
        //i位二进制位对应最高位的次数是i-1次;
        //取等
        i--;
        StringBuilder sb = new StringBuilder();
        for(int j = i; j >= 0; j--){
            int power = 1;
            for (int p = 0; p < j; p++) {
                power *= r;
            }
            if(n >= power){
                int  k =  n/power ;
                sb.append(k);
                n -= k * power;
            }else{
                sb.append(0);
            }
        }
        return sb.toString();
    }
}
