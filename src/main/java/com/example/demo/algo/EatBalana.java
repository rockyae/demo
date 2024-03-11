package com.example.demo.algo;

import java.util.Scanner;

public class EatBalana {
    /**
     * 30 11 23 4 20
     * 6
     * @param args
     */
    public static void main(String[] args) {


//        int[] trees = new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,
//                290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184};
//        long H = 823855818;
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int N = s.length;
        int[] trees = new int[N];
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(s[i]);
        }
        long H =sc.nextInt();
        long init = H;
        boolean goon = true;
        int K = 1;
        if(H == trees.length){
            int max = -1;
            for(int pile : trees){
                max = Math.max(pile,max);
            }
            System.out.println(max);
            return;
        }
        if(H < trees.length){
            System.out.println(0);
            return ;
        }

        while(goon){
            for (int i = 0; i < trees.length; i++) {
                //这棵树上的果子不够吃
                if(trees[i] < K){
                    H--;
                }else {
                    //如果能够整除
                    if(trees[i] % K == 0){
                        H -= trees[i]/K;
                    }else{
                        H -= trees[i]/K;
                        H--;
                    }
                }
            }
            if(H < 0){
                K++;
                H = init;
            }else{
                System.out.println(K);
                goon = false;
            }
        }
    }
}
