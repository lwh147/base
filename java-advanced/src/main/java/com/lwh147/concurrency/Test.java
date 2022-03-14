package com.lwh147.concurrency;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: 各种临时测试
 * @author: lwh
 * @create: 2021/7/30 10:58
 * @version: v1.0
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {
        FutureTask<Integer> task3 = new FutureTask<>(() -> {
            log.debug("hello");
            return 100;
        });

        new Thread(task3, "t3").start();

        try {
            Integer result = task3.get();
            log.debug("获取到了结果：{}", result);
            log.debug("获取到了结果：{}", result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            log.debug("任务出错");
        } finally {
            log.debug("任务结束");
        }

        log.debug("主程序结束");
    }


}

