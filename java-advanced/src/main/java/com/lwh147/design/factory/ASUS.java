package com.lwh147.design.factory;

/**
 * 华硕主板制造
 *
 * @author lwh
 * @date 2022/3/21 20:41
 **/
public class ASUS implements Motherboard {
    @Override
    public void make() {
        System.out.println("新的华硕主板");
    }
}
