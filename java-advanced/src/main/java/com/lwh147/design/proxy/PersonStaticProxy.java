package com.lwh147.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 人类接口的静态代理，静态代理需要根据代理的接口编写不同的代理类
 *
 * @author lwh
 * @date 2022/3/14 11:39
 **/
@Slf4j
public class PersonStaticProxy implements Person {
    private final Person person;

    public PersonStaticProxy(Person person) {
        this.person = person;
    }

    @Override
    public void identity() {
        log.info("前置操作");
        this.person.identity();
        log.info("后置操作");
    }

    public static void main(String[] args) {
        Student student = new Student();
        PersonStaticProxy personStaticProxy = new PersonStaticProxy(student);
        personStaticProxy.identity();
        personStaticProxy.name();
    }

    @Override
    public void name() {
        log.info("前置操作");
        this.person.name();
        log.info("后置操作");
    }
}
