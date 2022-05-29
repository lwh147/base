package com.lwh147.design.factory;

/**
 * CPU 制造工程
 *
 * @author lwh
 * @date 2022/3/21 20:48
 **/
public class CPUSimpleFactory {

    public CPU get(String brand) {
        if ("AMD".equals(brand)) {
            return new AMD();
        } else if ("INTEL".equals(brand)) {
            return new INTEL();
        } else {
            return null;
        }
    }
}
