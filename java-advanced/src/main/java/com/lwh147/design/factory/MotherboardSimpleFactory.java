package com.lwh147.design.factory;

/**
 * 主板制造工程
 *
 * @author lwh
 * @date 2022/3/21 20:43
 **/
public class MotherboardSimpleFactory {

    public Motherboard getMotherboard(String brand) {
        if ("ASUS".equals(brand)) {
            return new ASUS();
        } else if ("MSI".equals(brand)) {
            return new MSI();
        } else {
            return null;
        }
    }
}
