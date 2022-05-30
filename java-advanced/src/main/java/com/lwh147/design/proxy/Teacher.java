package com.lwh147.design.proxy;

/**
 * 教师类
 *
 * @author lwh
 * @date 2022/5/30 11:34
 **/
public class Teacher implements Person {
    @Override
    public void identity() {
        System.out.println("老师");
    }

    @Override
    public void name() {
        System.out.println("罗翔");
    }
}
