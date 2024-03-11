package com.example.demo.reflect;

import java.lang.reflect.Method;

public class ReflectTest {


    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> pointClass = Class.forName("com.example.demo.module.Point");
        Method[] methods = pointClass.getMethods();
        for(Method method : methods){
            System.out.println(method.getName());
            
        }
     }
}
