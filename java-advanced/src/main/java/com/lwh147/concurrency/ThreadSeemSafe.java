package com.lwh147.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 看起来线程安全的操作
 * @author: lwh
 * @create: 2021/8/4 14:06
 * @version: v1.0
 **/
public class ThreadSeemSafe {
    public static void main(String[] args) {
        // ThreadSeemSafe t = new ThreadSeemSafe();
        ThreadSeemSafeSubClass t = new ThreadSeemSafeSubClass();
        Thread t1 = new Thread(t::run, "t1");
        Thread t2 = new Thread(t::run, "t2");

        t1.start();
        t2.start();
    }

    public final void run() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            add(list);
            remove(list);
        }
    }

    private void add(List<String> list) {
        list.add("1");
    }

    private void remove(List<String> list) {
        list.remove(0);
    }
}

class ThreadSeemSafeSubClass extends ThreadSeemSafe {
    public void remove(List<String> list) {
        new Thread(() -> list.remove(0)).start();
    }
}

