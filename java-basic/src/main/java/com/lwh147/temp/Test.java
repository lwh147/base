package com.lwh147.temp;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/1/8 10:05
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {
        Hashtable table = new Hashtable();
        HashMap map = new HashMap();

        // 模拟随机hash
        final Random random = new Random();
        // 容量
        final int n = 16;

        for (int i = 0; i < 100; i++) {
            // 正整数
            int hash = random.nextInt() & 0x7FFFFFFF;
            int result1 = hash % n;
            int result2 = hash & (n - 1);
            System.out.println((result1 == result2));
        }
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
        log.info("异或运算无符号右移：{}", 15 & ("郭德纲".hashCode() ^ ("郭德纲".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("彭于晏".hashCode() ^ ("彭于晏".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("李小龙".hashCode() ^ ("李小龙".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("蔡徐鸡".hashCode() ^ ("蔡徐鸡".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("张三".hashCode() ^ ("张三".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("李四".hashCode() ^ ("李四".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("王五".hashCode() ^ ("王五".hashCode() >>> 16)));
        log.info("异或运算无符号右移：{}", 15 & ("赵六".hashCode() ^ ("赵六".hashCode() >>> 16)));
        log.info("\n");
        log.info("同或运算无符号右移：{}", 15 & ~("郭德纲".hashCode() ^ ("郭德纲".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("彭于晏".hashCode() ^ ("彭于晏".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("李小龙".hashCode() ^ ("李小龙".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("蔡徐鸡".hashCode() ^ ("蔡徐鸡".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("张三".hashCode() ^ ("张三".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("李四".hashCode() ^ ("李四".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("王五".hashCode() ^ ("王五".hashCode() >>> 16)));
        log.info("同或运算无符号右移：{}", 15 & ~("赵六".hashCode() ^ ("赵六".hashCode() >>> 16)));
        log.info("\n");
        log.info("异或运算有符号右移：{}", 15 & ("郭德纲".hashCode() ^ ("郭德纲".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("彭于晏".hashCode() ^ ("彭于晏".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("李小龙".hashCode() ^ ("李小龙".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("蔡徐鸡".hashCode() ^ ("蔡徐鸡".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("张三".hashCode() ^ ("张三".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("李四".hashCode() ^ ("李四".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("王五".hashCode() ^ ("王五".hashCode() >> 16)));
        log.info("异或运算有符号右移：{}", 15 & ("赵六".hashCode() ^ ("赵六".hashCode() >> 16)));
        log.info("\n");
        log.info("与运算无符号右移：{}", 15 & ("郭德纲".hashCode() & ("郭德纲".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("彭于晏".hashCode() & ("彭于晏".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("李小龙".hashCode() & ("李小龙".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("蔡徐鸡".hashCode() & ("蔡徐鸡".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("张三".hashCode() & ("张三".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("李四".hashCode() & ("李四".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("王五".hashCode() & ("王五".hashCode() >>> 16)));
        log.info("与运算无符号右移：{}", 15 & ("赵六".hashCode() & ("赵六".hashCode() >>> 16)));
        log.info("\n");
        log.info("或运算无符号右移：{}", 15 & ("郭德纲".hashCode() | ("郭德纲".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("彭于晏".hashCode() | ("彭于晏".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("李小龙".hashCode() | ("李小龙".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("蔡徐鸡".hashCode() | ("蔡徐鸡".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("张三".hashCode() | ("张三".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("李四".hashCode() | ("李四".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("王五".hashCode() | ("王五".hashCode() >>> 16)));
        log.info("或运算无符号右移：{}", 15 & ("赵六".hashCode() | ("赵六".hashCode() >>> 16)));
    }
}
