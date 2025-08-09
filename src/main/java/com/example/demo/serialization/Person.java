package com.example.demo.serialization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.*;


@AllArgsConstructor
@Data
public class Person implements Serializable {
    private static final long serialVersionUID =1L;

    private Integer age;

    private transient String name;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("x.file");
        //序列化
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Person p = new Person(18,"sean");
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        //将对象写入流
        oos.writeObject(p);

        //反序列化
        FileInputStream fileInputStream = new FileInputStream(file);////指定数据源的是文件
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);//从文件反序列化
        //从流中读取对象
        Object person = ois.readObject();

        System.out.println(person);

    }
}
