package com.example;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Tree {

    public class TreeNode<T> {
        T val;
        TreeNode left;
        TreeNode right;

        public TreeNode(T val) {
            this.val = val;
        }
    }

    public String concat(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 3 5
     * 2 4 2
     * 1 3
     * 3 5
     * 3 7
     * 5 9
     * 1 10
     * <p>
     * 20
     */
    @Data
    static class Person {
        int count;
        int money;


        public Person(int count, int money) {
            this.count = count;
            this.money = money;
        }

        public int getCount() {
            return count;
        }

        public int getMoney() {
            return money;
        }
    }
    static class Res{
        int pos;
        BigDecimal money;

        public Res(int pos, BigDecimal money) {
            this.pos = pos;
            this.money = money;
        }

        public int getPos() {
            return pos;
        }

        public BigDecimal getMoney() {
            return money;
        }
    }

    public static void main(String[] args) {
        //

        //2.最大单价*数量 数量取桌子容量和人数的较小者
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int tabLen = in.nextInt();
        int peopleCount = in.nextInt();
        int[] table = new int[tabLen];
        //1.初始化table容量
        for (int i = 0; i < table.length; i++) {
            table[i] = in.nextInt();
        }
        ArrayList<Person> peopleList = new ArrayList<>();
        for (int i = 0; i < peopleCount; i++) {
            int c = in.nextInt();
            int m = in.nextInt();
            Person person = new Person(c, m);
            peopleList.add(person);
        }
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i < table.length; i++) {
            int cap = table[i];
            if(peopleList.isEmpty()){
                break;
            }
            //1.算出还未选择的每批人数的单价，取最大的
            //返回索引，根据索引删除元素
            Res max = getMax(peopleList);
            int num = Math.min(peopleList.get(max.pos).getCount(), cap);
            BigDecimal temp = max.money.multiply(new BigDecimal(num));
            sum = sum.add(temp);
            peopleList.remove(max.pos);
        }
        System.out.println(sum);
    }

    static Res getMax(List<Person> list) {
        BigDecimal max = BigDecimal.ZERO;
        int pos = -1;
        for (int i = 0; i < list.size(); i++) {
            BigDecimal a = BigDecimal.valueOf(list.get(i).getCount());
            BigDecimal b = BigDecimal.valueOf(list.get(i).getMoney());
            BigDecimal temp = b.divide(a, 2, RoundingMode.HALF_UP);
            if (temp.compareTo(max) > 0) {
                max = temp;
                pos = i;
            }
        }
        return new Res(pos, max);
    }


}
