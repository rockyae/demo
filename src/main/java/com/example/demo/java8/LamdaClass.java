package com.example.demo.java8;

import com.example.demo.module.Point;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;

public class LamdaClass {

    public static void main(String[] args) {
        Integer[] arr = {1,98,2,90,4,7,10};
        Point point1 = new Point(1,2);
        Point point2 = new Point(1,3);
        Point point3 = new Point(2,2);
        Point point4 = new Point(7,2);
        //System.out.println(arr.stream().filter(a->a==4).collect(Collectors.toList()));
        Point[] points = {point1,point2,point3,point4};
        Arrays.sort(points,new Comparator<Point>(){
            @Override
            //方法返回负值时，代表 o1 < o2；当方法返回 0 时，代表 o1 = o2；当方法返回正值时，代表 o1 > o2;

            //小于的排前面，在降序规则下，”小的“就是大的数据，。例如，传入1 和 3 的时候，compare 返回 2，正值代表 a > b，即 1 “大于” 3，这个“大于”正是我们自己定义的规则，所以次序是3，1,就是降序。
            //总结
            public int compare(Point o1, Point o2) {
                if(o1.getX() == o2.getX()){
                    return o2.getY()-o1.getY();
                }else{
                    return o2.getX()-o1.getX();
                }
            }
        });
        System.out.println(Lists.newArrayList(points));
    }
}
