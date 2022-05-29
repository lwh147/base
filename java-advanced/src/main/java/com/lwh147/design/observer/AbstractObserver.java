package com.lwh147.design.observer;


/**
 * 观察者抽象类
 *
 * @author lwh
 * @date 2022/5/27 10:00
 **/
public abstract class AbstractObserver {
    private final Subject subject;

    protected AbstractObserver(Subject subject) {
        this.subject = subject;
    }

    protected Subject getSubject() {
        return this.subject;
    }

    public abstract void update();
}
