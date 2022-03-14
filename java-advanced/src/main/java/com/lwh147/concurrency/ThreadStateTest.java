package com.lwh147.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description: java 中线程的六种状态测试
 * @author: lwh
 * @create: 2021/8/3 11:05
 * @version: v1.0
 **/
@Slf4j
public class ThreadStateTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        }, "t1");
        Thread t2 = new Thread(() -> {
            while (true) {
                // do nothing
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");
        Thread t4 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4");
        Thread t5 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t5");
        Thread t6 = new Thread(() -> {

        }, "t5");

        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        log.debug("t1的状态：{}", t1.getState());
        t1.start();
        log.debug("t2的状态：{}", t2.getState());
        log.debug("t3的状态：{}", t3.getState());
        log.debug("t4的状态：{}", t4.getState());
        log.debug("t5的状态：{}", t5.getState());
        log.debug("t6的状态：{}", t6.getState());
    }
}
