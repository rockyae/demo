package com.example.demo.fanxing;

import java.util.ArrayList;
import java.util.List;

public class GenericTest<T> {
    private T t;

    public GenericTest(T t1){
        this.t = t1;
    }
    public <E> T getString(E e){
        return t;
    }

    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>();
//        list.add(1);
//        System.out.println(list);
        GenericTest<Integer> generic = new GenericTest<>(2);
        Integer n = generic.getString(1);
        System.out.println(n);

    }
}
