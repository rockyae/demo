package com.example.demo.systemcopy;

public class Test {



    private static Object[] arr = {};

    public static void main(String[] args) {
        Object[] des = {};
        try{
            System.arraycopy(arr,0,des,0,arr.length);
        }catch (Exception e){
          e.printStackTrace();
        }
    }
}
