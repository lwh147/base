package com.lwh147.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @description: park 线程测试
 * @author: lwh
 * @create: 2021/8/2 16:26
 * @version: v1.0
 **/
@Slf4j
public class ParkThreadTest {

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("线程正在运行...");
            LockSupport.park();
            log.debug("线程恢复运行，打断状态为：{}", Thread.currentThread().isInterrupted());
            // log.debug("线程恢复运行，打断状态为：{}", Thread.interrupted());
            LockSupport.park();
            log.debug("线程再次运行");
        }, "park");
        t1.start();
        log.debug("线程状态：{}", t1.getState());
        TimeUnit.SECONDS.sleep(1);
        log.debug("主线程想要打断park线程");
        log.debug("线程状态：{}", t1.getState());
        t1.interrupt();
    }
}
