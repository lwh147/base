package com.lwh147.design.proxy;

/**
 * 学生类
 *
 * @author lwh
 * @date 2022/3/14 11:38
 **/
public class Student implements Person {
    @Override
    public void identity() {
        System.out.println("学生");
    }

    @Override
    public void name() {
        System.out.println("张三");
    }
}
