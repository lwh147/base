package com.lwh147.concurrency;


import lombok.extern.slf4j.Slf4j;

/**
 * @description: 测试并发安全性问题
 * @author: lwh
 * @create: 2021/8/3 14:09
 * @version: v1.0
 **/
@Slf4j
public class ConcurrencyTest {
    static final Object lock = new Object();
    static final Object lock2 = new Object();
    static final Room room = new Room();
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    public static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.increment();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("i：{}", room.getCount());
    }

    public static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // synchronized (lock)
            for (int j = 0; j < 5000; j++) {
                synchronized (lock) {
                    i++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            // synchronized (lock)
            for (int j = 0; j < 5000; j++) {
                synchronized (lock) {
                    // synchronized (lock2) {
                    i--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("i：{}", i);
    }
}

class Room {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            this.count++;
        }
    }

    public void decrement() {
        synchronized (this) {
            this.count--;
        }
    }

    public int getCount() {
        synchronized (this) {
            return this.count;
        }
    }
}
