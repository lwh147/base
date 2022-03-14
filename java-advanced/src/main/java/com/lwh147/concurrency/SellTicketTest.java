package com.lwh147.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @description: 卖票问题
 * @author: lwh
 * @create: 2021/8/5 9:56
 * @version: v1.0
 **/
public class SellTicketTest {
    static Random random = new Random();

    public static void main(String[] args) {
        // 售票窗口
        Window window = new Window(1000);
        // 售出的票数
        List<Integer> sellCount = new Vector<>();
        // 所有的线程集合
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {
                int amount = window.sell(randomAmount());
                sellCount.add(amount);
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("售出的票数：" + sellCount.stream().mapToInt(i -> i).sum());
        System.out.println("剩余票数：" + window.getCount());
    }

    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}

class Window {
    private int count;

    public Window(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    public int sell(int amount) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (count >= amount) {
            count -= amount;
            return amount;
        }
        return 0;
    }
}