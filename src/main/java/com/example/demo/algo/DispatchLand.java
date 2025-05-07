package com.example.demo.algo;

import java.util.*;

public class DispatchLand {

    /**
     * 分配可以覆盖相同数字的土地，计算最大的土地面积
     * 输入: 二维数组表示土地，数字表示旗子
     * 输出: 所有数字中对应的最大的土地面积
     * @param args
     */
    public static void main(String[] args) {

        //存储单个数字对应的所有旗子下标  1-List<int []>
        //list.get(0)[0] = x; minx,maxX;
        Scanner sc = new Scanner(System.in);
        String[] nums = sc.nextLine().split(" ");
        int m = Integer.parseInt( nums[0]);
        int n = Integer.parseInt( nums[1]);
        int[][] land = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                land[i][j] = sc.nextInt();
            }
        }
        HashMap<Integer, List<Integer[]>> numIndexMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(land[i][j] != 0){
                    if(!numIndexMap.containsKey(land[i][j])){
                        Integer[] firstIndex = {i,j};
                        ArrayList<Integer[]> indexs = new ArrayList<>();
                        indexs.add(firstIndex);
                        numIndexMap.put(land[i][j], indexs);
                    }else{
                        List<Integer[]> indexs = numIndexMap.get(land[i][j]);
                        Integer[] otherIndex = {i,j};
                        indexs.add(otherIndex);
                        numIndexMap.put(land[i][j], indexs);
                    }
                }
            }
        }
        int area = 0;
        for(Integer num : numIndexMap.keySet()){
            int minX = m;
            int minY = n;
            int maxX = -1;
            int maxY = -1;
            List<Integer[]> indexs = numIndexMap.get(num);
            for(Integer[] index : indexs){
                minX = Math.min(minX, index[0]);
                maxX = Math.max(maxX, index[0]);
                minY = Math.min(minY, index[1]);
                maxY = Math.max(maxY, index[1]);
            }
            area = Math.max(area, (maxX-minX+1)*(maxY-minY+1));

        }
        System.out.println(area);
    }
}
