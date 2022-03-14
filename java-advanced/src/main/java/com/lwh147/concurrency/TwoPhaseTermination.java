package com.lwh147.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * @description: 两阶段终止模式
 * @author: lwh
 * @create: 2021/8/2 15:49
 * @version: v1.0
 **/
@Slf4j
public class TwoPhaseTermination {
    private Thread monitor;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                boolean b = Thread.currentThread().isInterrupted();
                if (b) {
                    // 料理后事
                    log.debug("料理后事");
                    break;
                } else {
                    // 做数据记录
                    log.debug("做数据记录");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        // 捕获到打断异常，重新设置打断状态，在下一次循环中结束本线程
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }, "moniter");

        monitor.start();

    }

    // 终止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
