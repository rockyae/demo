package com.example.demo.algo;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class YuanyinString {


    public static void main(String[] args) {
        //输入字符串， 指定瑕疵度
        //输出指定瑕疵度最长元音串的长度

        //输入
        Scanner sc = new Scanner(System.in);
        Integer tar = Integer.parseInt(sc.nextLine());
        String oriStr = sc.nextLine();
//        int tar = 2;
//        String oriStr  = "asdbu";
        //处理
        HashSet yuanyinStr = new HashSet<Character>(Arrays.asList('a', 'e', 'i', 'o', 'u','A', 'E', 'I', 'O', 'U'));
        int len = 1;

        int max = 0;
        while ((len <= oriStr.length())){

            for(int start = 0, end; (end = start+len-1) < oriStr.length(); start++){
                String subString = oriStr.substring(start, end+1);
                char[] temp = subString.toCharArray();
                if(yuanyinStr.containsAll(Arrays.asList(temp[0],temp[temp.length-1]))){
                    //计算字串辅音字母数量
                    int count = 0;
                    for(char c : temp){
                        if(!yuanyinStr.contains(c)){
                            count++;
                        }
                    }
                    if(count == tar){
                        max = Math.max(max, subString.length());
                    }
                }
            }
            len++;
        }
        System.out.println(max);
    }

}
