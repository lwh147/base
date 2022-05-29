package com.lwh147.temp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/1/8 10:05
 **/
public class Test {
    public static void main(String[] args) {
        // Hashtable table = new Hashtable();
        // HashMap map = new HashMap();
        // String s = new String();
        // System.out.println(new Integer(-1).hashCode());

        // 容量
        // int n = 16;
        // 随机关键码
        // final Random random = new Random();

        // for (int i=0; i<100; i++) {
        //     // 必须为正整数
        //     int hash = random.nextInt() & 0x7FFFFFFF;
        //     int result1 = hash % n;
        //     int result2 = hash & (n - 1);
        //     System.out.println(result1 == result2);
        // }

        // Integer int1 = 10, int2 = 11;
        // System.out.println(int2 == 11);
        // log.info("^ 运算：{}", 15 & ("郭德纲".hashCode() ^ ("郭德纲".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("彭于晏".hashCode() ^ ("彭于晏".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("李小龙".hashCode() ^ ("李小龙".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("蔡徐鸡".hashCode() ^ ("蔡徐鸡".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("张三".hashCode() ^ ("张三".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("李四".hashCode() ^ ("李四".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("王五".hashCode() ^ ("王五".hashCode() >>> 16)));
        // log.info("^ 运算：{}", 15 & ("赵六".hashCode() ^ ("赵六".hashCode() >>> 16)));
        //
        // log.info("同或运算：{}", 15 & ~("郭德纲".hashCode() ^ ("郭德纲".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("彭于晏".hashCode() ^ ("彭于晏".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("李小龙".hashCode() ^ ("李小龙".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("蔡徐鸡".hashCode() ^ ("蔡徐鸡".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("张三".hashCode() ^ ("张三".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("李四".hashCode() ^ ("李四".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("王五".hashCode() ^ ("王五".hashCode() >>> 16)));
        // log.info("同或运算：{}", 15 & ~("赵六".hashCode() ^ ("赵六".hashCode() >>> 16)));
        Hashtable table = new Hashtable();
        HashMap map = new HashMap();

        // test2();
        test3();
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

    public static void test2() {
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add(null);
        list.add("1");
        list.stream().filter(o -> "1".equals(o.trim())).forEach(System.out::println);
    }

    public static void test3() {
        System.out.println(new BigDecimal("0.00").negate().toPlainString());
    }
}
