package com.example.demo.proxy;

public class Client {
    public static void main(String[] args) {
    try{
//        PrintNum proxy0 = (PrintNum) new MyHandler(new MyObject(1)).getProxy();//会抛异常
//        proxy0.print();
        //生成代理对象，调用代理对象的方法时，会自动调用handler的invoke方法
        MyObject o = new MyObject(1);
        Object proxy = new MyHandler(o).getProxy();
        ((PrintNum) proxy).print();
        ((PrintNum) proxy).play();

    }catch (Exception e){
        e.printStackTrace();
    }

    }
}
