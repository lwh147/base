package com.lwh147.design.proxy;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/3/14 11:38
 **/
public class Student implements Person {
    @Override
    public void identity() {
        System.out.println("学生");
    }
}
