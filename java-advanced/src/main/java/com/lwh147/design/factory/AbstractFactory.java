package com.lwh147.design.factory;

/**
 * PC抽象工厂
 *
 * @author lwh
 * @date 2022/3/21 20:52
 **/
public abstract class AbstractFactory {

    public static AbstractFactory getFactory(String part) {
        if ("CPU".equals(part)) {
            return new CPUFactory();
        } else if ("Motherboard".equals(part)) {
            return new MotherboardFactory();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory("CPU");
        assert factory != null;
        CPU cpu = factory.getCPU("AMD");
        cpu.make();
    }

    public abstract CPU getCPU(String brand);

    public abstract Motherboard getMotherboard(String brand);
}
