package com.lwh147.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

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

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), Person.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);

        Person proxyStudent = (Person) constructor.newInstance((InvocationHandler) (proxy, method, args1) -> {
            log.info("proxy对象：{}", proxy);
            log.info("method对象：{}", method);
            log.info("args1对象：{}", args1);
            if (Object.class.equals(method.getDeclaringClass())) {
                log.info("代理对象的其它方法被调用");
                return method.invoke(new Object(), args1);
            } else {
                log.info("我是代理对象的identity方法");
                return 1;
            }
        });

        System.out.println(proxyClass);
        System.out.println(constructor);

        proxyStudent.identity();
        // log.info(proxyStudent.toString());
    }

    @Override
    public void identity() {
        log.info("前置操作");
        this.person.identity();
        log.info("后置操作");
    }
}
