package com.example.demo.algo;

import cn.hutool.core.text.csv.CsvUtil;

import java.util.Scanner;

/**
 * 输入: 10001
 * 输出： 1
 */
public class FindSeat {
    //遍历每个位置，如果当前位置为1，找下一个位置；如果为0，判断左右两边位置是否为1；
    //0且两边均为0算一个有效位置，把0改为1，累加所有位置;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        int count = 0;
        if(arr.length == 1 && arr[0]=='0'){
            count++;
            return;
        }
        //len >=2
        int l,r =0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == '0'){
                //第一个位置,判断右边是否
                if(i == 0){
                    r = i+1;
                    if(arr[r] == '0'){
                        count++;
                        arr[i] = '1';
                    }
                }else if(i == arr.length-1){
                    l = i-1;
                    if(arr[l] == '0'){
                        count++;
                        arr[i] = '1';
                    }
                    //len>=3才会进这个分支
                }else{
                    l = i-1;
                    r = i+1;
                    if(arr[l] == '0'&& arr[r] =='0'){
                        count++;
                        arr[i] = '1';
                    }

                }
            }

        }
        System.out.println(count);
    }


}
