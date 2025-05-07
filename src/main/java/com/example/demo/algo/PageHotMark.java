package com.example.demo.algo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 输入:
 * 10
 * 1 2 1 2 1 2 1 2 1 2
 * 5
 * 输出: 2 (达到阈值有2个页,按照访问次数降序，次数相同按页号大小排列)
 *      1
 *      2
 */
public class PageHotMark {
    public static void main(String[] args) {
        //统计每个页的访问次数，达到阈值的 1-5
        //treeMap
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        //1-5
        //优先队列存储键值对
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i=0; i<N; i++){
            int num = sc.nextInt();
            treeMap.put(num, treeMap.getOrDefault(num, 0)+1);
        }
        int limit = sc.nextInt();
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                if(!Objects.equals(entry1.getValue(), entry2.getValue())){
                    return entry2.getValue().compareTo(entry1.getValue());
                }else {
                    return entry1.getKey().compareTo(entry2.getKey());
                }
            }
        });
        queue.addAll(treeMap.entrySet());
        List<Integer> list = queue.stream().filter(e -> e.getValue() >= limit).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(list.size());
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
