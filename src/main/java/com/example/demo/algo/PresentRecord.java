package com.example.demo.algo;


import java.util.Scanner;

/**
 * 考勤记录
 */
public class PresentRecord {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //待处理记录行数
        int N = Integer.parseInt(sc.nextLine());
        while(N-- > 0){
            String[] record = sc.nextLine().split(" ");
            System.out.print(canReward(record)? "true " : "false ");
        }
    }

    static boolean canReward(String[] record){
        //1.缺勤不超过一次
        //2.没有连续的迟到/早退
        //3.任意连续7次考勤，异常不超过3次
        //满足上述条件，返回true;
        int absentCount = 0;

        for(int i=0; i<record.length; i++){
            //1.缺勤不超过一次
            if(record[i].equals("absent")){
                absentCount++;
                if(absentCount > 1){
                    return false;
                }
            }
            //2.没有连续的迟到/早退
            if(i >0 && (record[i].equals("late") || record[i].equals("leaveearly"))){
                if(record[i-1].equals("late") || record[i-1].equals("leaveearly")){
                    return false;
                }
            }
            if(i >= 6){
                int error = 0;
                for(int j=i-6; j<=i; j++){
                    if(!record[j].equals("present")){
                        error++;
                    }
                }
                if(error > 3) return false;
            }
        }
        return true;
    }
}
