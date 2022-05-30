package com.lwh147.design.proxy;

/**
 * 狗类
 *
 * @author lwh
 * @date 2022/5/30 11:40
 **/
public class Dog implements Animal {
    @Override
    public void species() {
        System.out.println("狗");
    }

    @Override
    public void name() {
        System.out.println("旺财");
    }
}
