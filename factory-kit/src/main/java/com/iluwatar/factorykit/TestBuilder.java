package com.iluwatar.factorykit;

import java.util.function.Supplier;

public class TestBuilder {
    void build1(WeaponType name, Supplier<Weapon> supplier) {
        System.out.println("build for " + name);
        //这里get出来的是App.java中builder的add方法的第二个参数，实际上在App.java中调用
        //builder的add方法就是调用这里的build1方法
        System.out.println(supplier.get());
    }

    Supplier<Weapon> build2(WeaponType name, Supplier<Weapon> supplier) {
        System.out.println("build for " + name);
        System.out.println(supplier.get());
        return supplier;
    }

    Weapon create(WeaponType name) {
        return new Axe();
    }
}
