package com.lwh147.design.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理使用，不管代理什么接口，一个类即可实现代理
 *
 * @author lwh
 * @date 2022/3/21 21:10
 **/
@Slf4j
public class DynamicProxy implements InvocationHandler {
    /**
     * 被代理对象
     **/
    private final Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     **/
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Student student = new Student();
        DynamicProxy studentProxyHandler = new DynamicProxy(student);
        Person personProxy = (Person) studentProxyHandler.newProxyInstance();
        personProxy.identity();
        personProxy.name();

        Cat cat = new Cat();
        DynamicProxy catProxyHandler = new DynamicProxy(cat);
        Animal animalProxy = (Animal) catProxyHandler.newProxyInstance();
        animalProxy.species();
        animalProxy.name();
    }

    /**
     * 对代理对象所实现的接口方法的调用最终都会通过此方法
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("前置操作");
        Object result = method.invoke(target, args);
        log.info("后置操作");
        return result;
    }
}
