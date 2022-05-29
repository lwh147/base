package com.lwh147.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * 某个观察者
 *
 * @author lwh
 * @date 2022/5/27 10:10
 **/
@Slf4j
public class Observer extends AbstractObserver {

    public Observer(Subject subject) {
        super(subject);
        subject.registerObserver(this);
    }

    @Override
    public void update() {
        log.info("目标发生改变：{}", super.getSubject().toString());
    }
}
