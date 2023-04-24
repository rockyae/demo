package com.example.demo.deepcopy;

import com.example.demo.module.Point;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
public class DeepCopy {

    public static void main(String[] args) {
//        Point point = new Point(1,2);
//        System.out.println("修改前:"+point);
//        modifyPoint(point);
//        modifyPointV2(point);//只存在值传递
//        System.out.println("修改后:"+point);
        logTest();
    }

    public static void modifyPoint(Point p){
        p.setX(5);
        p.setY(6);
        System.out.println("方法里："+p);
    }

    public static void modifyPointV2(Point p){
         p = Point.builder().x(7).y(8).build();
        System.out.println("方法里："+p);
    }

    public static void logTest(){
        BigDecimal c = new BigDecimal("1.3e+7");
        BigDecimal d = new BigDecimal("1.23E-3");
        BigDecimal e = new BigDecimal("1.23E+0");
        BigDecimal f = new BigDecimal("0");
        System.out.println(c.toPlainString());
        System.out.println(d.toPlainString());
        System.out.println(e.toPlainString());
        System.out.println(f);
    }


}
