package com.lwh147.design.proxy;

/**
 * 猫类
 *
 * @author lwh
 * @date 2022/5/30 11:39
 **/
public class Cat implements Animal {
    @Override
    public void species() {
        System.out.println("猫");
    }

    @Override
    public void name() {
        System.out.println("大橘");
    }
}
