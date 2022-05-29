package com.lwh147.design.factory;

/**
 * INTEL CPU 制造方法
 *
 * @author lwh
 * @date 2022/3/21 20:46
 **/
public class INTEL implements CPU {
    @Override
    public void make() {
        System.out.println("新的Intel I5 12600k");
    }
}
