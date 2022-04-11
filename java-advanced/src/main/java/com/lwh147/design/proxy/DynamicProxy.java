package com.lwh147.design.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * jdk动态代理使用
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

    /**
     * 对代理对象实现的所有接口方法的调用最终都会通过调用此方法
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("前置操作");
        Object result = method.invoke(target, args);
        log.info("后置操作");
        return result;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Student target = new Student();
        // DynamicProxy proxy = new DynamicProxy(target);
        // Person studentProxy = (Person) proxy.newProxyInstance();
        // studentProxy.identity();
    }
}
