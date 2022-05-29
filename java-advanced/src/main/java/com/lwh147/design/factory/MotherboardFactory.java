package com.lwh147.design.factory;

/**
 * TODO
 *
 * @author lwh
 * @date 2022/3/21 21:02
 **/
public class MotherboardFactory extends AbstractFactory{
    @Override
    public CPU getCPU(String brand) {
        return null;
    }

    @Override
    public Motherboard getMotherboard(String brand) {
        //撒范德萨的
        if ("ASUS".equals(brand)) {
            return new ASUS();
        } else if ("MSI".equals(brand)) {
            return new MSI();
        } else {
            return null;
        }
    }
}
