package com.lwh147.design.factory;

/**
 * 微星主板制造
 *
 * @author lwh
 * @date 2022/3/21 20:41
 **/
public class MSI implements Motherboard {
    @Override
    public void make() {
        System.out.println("新的微星主板");
    }
}
