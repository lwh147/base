package com.lwh147.test;

/**
 * @description: 测试类
 * @author: lwh
 * @create: 2020/11/26 22:10
 * @version: v1.0
 **/
class Test {
    static int x;
    static double y;

    static {
        x = 1;
        System.out.println("父类静态代码块1：" + x + "  " + y);
    }

    static {
        y = 1.1f;
        System.out.println("父类静态代码块2：" + x + "  " + y);
    }

    public Test() {
        System.out.println("父类空构造器");
    }

    public static void main(String[] args) {
    }

    void test() {
        x = 1;
        System.out.println("父类test方法");
    }

    void test(int a) {
        System.out.println("父类静态test方法");
    }

}

class SubTest extends Test {
    static int i;

    static {
        int j = 1;
        i = 1;
        System.out.println("子类静态代码块：" + i + "  " + j);
    }

    public SubTest() {
        System.out.println("子类空构造器");
    }

    public SubTest(int a) {
        System.out.println("子类a构造器");
    }

    void test() {
        System.out.println("子类test方法");
    }

    void test(int a) {
        System.out.println("子类test方法a");
    }

}

class SSubTest extends SubTest {
    static int i;

    static {
        int j = 1;
        i = 1;
        System.out.println("孙子类静态代码块：" + i + "  " + j);
    }

    public SSubTest() {
        System.out.println("孙子类空构造器");
    }

    public SSubTest(int a) {
        System.out.println("孙子类a构造器");
    }

    void test() {
        System.out.println("孙子类test方法");
    }
}
