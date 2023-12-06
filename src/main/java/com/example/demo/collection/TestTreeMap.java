package com.example.demo.collection;

import java.util.Comparator;
import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        //无参构造
//		TreeMap tree = new TreeMap();
        //创建外比较器对象，定义比较规则
        Comparator com = new ComparaStringLength();
        TreeMap tree = new TreeMap(com);
        tree.put("hello1", 123);
        tree.put("world11", 456);
        tree.put("hello", 321);
        tree.put("java", 789);
        System.out.println("元素的个数：" + tree.size());
        System.out.println(tree);
    }
}
class ComparaStringLength implements Comparator{
    /**
     * 比较规则：根据字符串的长度进行比较
     */
    @Override
    public int compare(Object o1, Object o2) {
        String s1 = (String)o1;
        String s2 = (String)o2;
        return s1.length() - s2.length();
    }

}
