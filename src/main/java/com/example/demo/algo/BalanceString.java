package com.example.demo.algo;

public class BalanceString {

    /**
     * 输入一个只含两个字符的平衡串，返回可以分割的平衡子串的最大数目
     * @param args
     */
    public static void main(String[] args) {
        String bStr = "XYXYXYYXX";
        char[] charArray = bStr.toCharArray();

        //遇X +1, 遇Y，-1
        int count = 0;
        int ans = 0;
        for(char c : charArray){
            if(c == 'X'){
                count++;
            }else{
                count--;
            }
            if(count == 0){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
