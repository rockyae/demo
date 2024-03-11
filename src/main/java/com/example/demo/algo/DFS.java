package com.example.demo.algo;

import java.util.Arrays;
import java.util.Scanner;

public class DFS {


    /**
     * 输出最大服务器数量
     *
     * 16:31 50 17:28
     * @param args
     */
    private static int max;
    static int m,n,count;
    private static int[][] spaces;
    private static  boolean[][] visited;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nums = sc.nextLine().split(" ");
         m = Integer.parseInt( nums[0]);
         n = Integer.parseInt( nums[1]);

        spaces = new int[m][n];
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                spaces[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //复位
                for (int k = 0; k < visited.length; k++) {
                    Arrays.fill(visited[k], false);
                }
                count = 0;
                dfs(i,j,visited);
                max = Math.max(max, count);
            }
        }
        System.out.println(max);
    }

    public static void dfs(int i,int j, boolean[][] visited){
        //无需加入
        if( i<0 || i>=m || j<0 || j>=n || visited[i][j] || spaces[i][j]==0){
            return ;
        }
        visited[i][j] = true;
        count++;
        dfs(i-1, j, visited);
        dfs(i+1, j, visited);
        dfs(i, j-1, visited);
        dfs(i, j+1, visited);
    }

}
