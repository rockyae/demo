package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler implements InvocationHandler{

    private MyObject o;

    public MyHandler(MyObject o) {
        this.o = o;
    }

    public Object getProxy(){
        //使用jdk提供的Proxy对象
        //这里用于生成o的代理对象
        System.out.println("执行了getProxy方法");
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = method.invoke(o,args);
        System.out.println(proxy.getClass().getName()+"执行了"+method.getName()+"返回了"+res);
        return  res;
    }
}
