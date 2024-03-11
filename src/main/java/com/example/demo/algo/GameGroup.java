package com.example.demo.algo;

import java.util.Arrays;
import java.util.Scanner;

public class GameGroup {
    static int res = Integer.MAX_VALUE;
    static int totalSum =0;
    static int targetSum =0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int num : nums){
            totalSum += num;
        }
        targetSum = totalSum / 2;
        dfs(nums, 0, 0,0);
        System.out.println(res);
        sc.close();
    }

    static  void dfs(int[] nums, int idx, int count, int currentSum){
        if(count == 5){
            int otherSum = totalSum - currentSum;
            res = Math.min(res, Math.abs(currentSum - otherSum));
            return;
        }
        if(idx == 10) return;

        dfs(nums, idx+1, count+1, currentSum + nums[idx]);

        dfs(nums, idx+1, count, currentSum);
    }
}
