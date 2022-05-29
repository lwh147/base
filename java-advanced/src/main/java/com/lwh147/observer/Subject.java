package com.lwh147.observer;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察对象
 *
 * @author lwh
 * @date 2022/5/27 10:07
 **/
@ToString(exclude = "observerList")
public class Subject {
    private transient final List<AbstractObserver> observerList = new ArrayList<>(16);

    private int state;


    public Subject(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        this.notifyObservers();
    }

    public void notifyObservers() {
        for (AbstractObserver abstractObserver : this.observerList) {
            abstractObserver.update();
        }
    }

    public List<AbstractObserver> getObserverList() {
        return observerList;
    }

    public void registerObserver(Observer observer) {
        this.observerList.add(observer);
    }

    public static void main(String[] args) {
        Subject subject = new Subject(1);
        Observer observer = new Observer(subject);
        subject.setState(2);
        subject.setState(3);
        subject.setState(4);
        subject.setState(5);
    }
}
