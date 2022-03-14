package com.lwh147.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO
 * @author: lwh
 * @create: 2021/7/30 11:38
 * @version: v1.0
 **/
@Slf4j
public class JoinTest {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleeping...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("线程t1的状态：{}", t1.isInterrupted());
        log.debug("main 运行结束");
    }
}
