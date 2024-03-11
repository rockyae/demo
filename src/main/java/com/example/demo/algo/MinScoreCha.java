package com.example.demo.algo;

import java.util.*;

import static java.util.Comparator.comparingInt;

public class MinScoreCha {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        List<int[]> scoreList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            scoreList.add(new int[]{sc.nextInt(), sc.nextInt()});

        }
        sc.close();
        //按照整数大小（分值）进行升序排序
        scoreList.sort(comparingInt(a -> a[1]));

        int minDiff = Integer.MAX_VALUE;

        List<int[]> pairs = new ArrayList<>();

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int curDiff = scoreList.get(j)[1] - scoreList.get(i)[1];

                //
                if(curDiff < minDiff){
                    pairs.clear();
                    minDiff = curDiff;
                    //将当前的员工ID对添加到pairs中，保证ID较小的在前
                    pairs.add(new int[]{Math.min(scoreList.get(i)[0], scoreList.get(j)[0]),Math.max(scoreList.get(i)[0], scoreList.get(j)[0])});
                }else if(curDiff == minDiff){
                    pairs.add(new int[]{Math.min(scoreList.get(i)[0], scoreList.get(j)[0]),Math.max(scoreList.get(i)[0], scoreList.get(j)[0])});
                }else {
                    //因为分数升序排列，后面分差只会更大，直接跳过
                    break;
                }
            }
        }

        //对找到的ID对进行排序，先按第一个ID排序，如果第一个ID相同，按第二个ID排序
        pairs.sort(Comparator.<int[]>comparingInt(c -> c[0]).thenComparing(c -> c[1]));
        for(int[] pair : pairs){
            System.out.println(pair[0]+" "+pair[1]);
        }
    }
}
