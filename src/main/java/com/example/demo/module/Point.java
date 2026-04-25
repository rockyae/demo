package com.example.demo.module;


//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import com.example.demo.serialization.Person;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;


@Component
public class Point implements Serializable , ApplicationContextAware {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    int x;
    @Setter
    @Getter
    int y;
    private String ENV_NAME ;


    @Getter
    private Person person;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        //System.out.println("无参构造器被调用");
        System.out.println(Thread.currentThread().getName()+"在运行");
    }

    public void setPerson(Person person) {
        System.out.println("setPerson方法被调用");
        this.person = person;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ENV_NAME = applicationContext.getApplicationName();
        System.out.println("当前环境："+this.ENV_NAME);
    }


    public static void main(String[] args) {
//        HashSet<String> set = new HashSet<>();
//        set.add("china");
//        set.add("china");
//        set.add("america");
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        TreeMap<String,String> map = new TreeMap<>();
        map.put("k2","val1");
        map.put("k1","val1");
        Iterator iterator = map.keySet().iterator();
                while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
