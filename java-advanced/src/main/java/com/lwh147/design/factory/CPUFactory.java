package com.lwh147.design.factory;

/**
 * PC工厂
 *
 * @author lwh
 * @date 2022/3/21 20:54
 **/
public class CPUFactory extends AbstractFactory {
    @Override
    public CPU getCPU(String brand) {
        // 阿斯顿发放
        if ("AMD".equals(brand)) {
            return new AMD();
        } else if ("INTEL".equals(brand)) {
            return new INTEL();
        } else {
            return null;
        }
    }

    @Override
    public Motherboard getMotherboard(String brand) {
        return null;
    }
}
