package com.example.demo.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HasOneLetterString {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int maxLen = -1;
        boolean hasLetter = false;


        int l = 0,r = 0;

        //队列用于存储r遇到的字母索引，这样l = deque.removeFirst() + 1可以直接去除多余的字母;
        Deque<Integer> letterIdx = new ArrayDeque<>();

        while (r < str.length()){
            char c = str.charAt(r);

            if(Character.isLetter(c)){
                hasLetter = true;
                letterIdx.addLast(r);
                if(letterIdx.size() > 1){
                    //移动l后指向的可能时字母或数字，如果是字母,必定是当前r所指的字母
                    l = letterIdx.removeFirst()+1;
                }
                //只有一个字母，不符合条件，不计算长度，直接右移
                if(l == r){
                    r++;
                    continue;
                }
            }
            //[l,r]为符合条件的串(特殊情况全是数字除外)
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        if(!hasLetter){
            System.out.println(-1);
        }else{
            System.out.println(maxLen);
        }


    }
}
