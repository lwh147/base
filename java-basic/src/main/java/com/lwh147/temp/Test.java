package com.lwh147.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/1/8 10:05
 **/
public class Test {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(-123);
        integerList.add(-1);
        integerList.add(123);
        integerList.stream().sorted(Integer::compareTo).forEach(System.out::println);

        test1();
    }

    public static void test1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.forEach(s -> {
            System.out.println("执行了添加");
            if (s.equals("1")) {
                throw new RuntimeException("test");
            }
        });
        System.out.println("执行了最后");
        list.forEach(System.out::println);
    }
}
