//package com.example.demo.java8.stream;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
///**
// * @Title: TestStream
// * @Author rockyae
// * @Package com.example.demo.java8.stream
// * @Date 2025/8/23 16:08
// * @description:
// */
//public class TestStream {
//    public static void main(String[] args) {
//        //无限流，foreach将无限循环打印hello
////        Stream<String> generate = Stream.generate(() -> "hello");
////        Stream<BigDecimal> iterate = Stream.iterate(BigDecimal.ZERO,
////                n -> n.add(BigDecimal.ONE));
////        iterate.forEach(System.out::println);
//        //generate.forEach(System.out::println);
//        List<String> words = new ArrayList<>();
//        String s1 = "hello \uD83D\uDE00";
//        words.add("H");//65+7
//        words.add("e");
//        words.add("l");
//        words.add("\uD83D\uDE00");//一个码元唯一标识一个 Unicode 字符
//        System.out.println(s1.codePoints());
//        //每一个流元素都会调用mapper，并且在调用期间传递给consumer的元素都会添加到result
//        //相比flatMap ,mapMulti比flatMap更高效，因为它避免了创建中间集合，直接进行流式处理。
//        Stream<String> stream = words.stream();
//        Stream<String> result = stream.mapMulti((s, consumer) -> {
//            //将字符转为对应的码点
//            int i = 0;
//            while (i < s.length()) {
//                int cp = s.codePointAt(i);
//                consumer.accept("" + cp);
//                //是否是unicode增补字符
//                if (Character.isSupplementaryCodePoint(cp)) {
//                    i += 2;
//                } else {
//                    i++;
//                }
//            }
//        });
//        result.forEach(System.out::println);
//
//    }
//}
