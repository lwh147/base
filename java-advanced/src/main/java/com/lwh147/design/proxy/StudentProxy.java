package com.lwh147.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态代理
 *
 * @author lwh
 * @date 2022/3/14 11:39
 **/
@Slf4j
public class StudentProxy implements Person {
    private final Person person;

    public StudentProxy(Student student) {
        this.person = student;
    }

    @Override
    public void identity() {
        log.info("前置操作");
        this.person.identity();
        log.info("后置操作");
    }
}
