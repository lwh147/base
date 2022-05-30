package com.lwh147.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 动物静态代理类
 *
 * @author lwh
 * @date 2022/5/30 15:14
 **/
@Slf4j
public class AnimalStaticProxy implements Animal {
    private final Animal animal;

    public AnimalStaticProxy(Animal animal) {
        this.animal = animal;
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        AnimalStaticProxy animalStaticProxy = new AnimalStaticProxy(cat);
        animalStaticProxy.species();
        animalStaticProxy.name();
    }

    @Override
    public void species() {
        log.info("前置操作");
        this.animal.species();
        log.info("后置操作");
    }

    @Override
    public void name() {
        log.info("前置操作");
        this.animal.name();
        log.info("后置操作");
    }
}
