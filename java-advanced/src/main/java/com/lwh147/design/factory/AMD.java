package com.lwh147.design.factory;

/**
 * AMD CPU制造方法
 *
 * @author lwh
 * @date 2022/3/21 20:46
 **/
public class AMD implements CPU{
    @Override
    public void make() {
        System.out.println("新的AMD Ryzen5 5600X");
    }
}
