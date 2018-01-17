/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.factorykit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*
简单工厂方法也叫静态工厂方法，实际上就是根据不同的参数类型返回不同的类实例，这些类实例一般都继承自一个父类，如
Product createProduct(String proname){
	if ( "A" == proname ) {
		return new ConcreteProductA();
	} else if("B" == proname) {
		return new ConcreteProductB();
	}
	return null;
}
 */

public interface WeaponFactory {

    Weapon create(WeaponType name);

    //方法参数是Consumer接口类型，Consumer接口只有一个方法void accept(T t)接收一个T类型的参数，不返回任何值
    //而是在参数上做某些操作，如App.java中main方法中定义的consumer是对传入的Builder对象执行若干次add操作。
    static WeaponFactory factory(Consumer<Builder> consumer) {
        Map<WeaponType, Supplier<Weapon>> map = new HashMap<>();
        //参数是Consumer<Builder>类型的，所以这里的accept传入的应该是Builder类型的，而这里传入的是map::put，因为
        //这里的map是Map<WeaponType, Supplier<Weapon>>类型的，所以map的put方法参数就是WeaponType, Supplier<Weapon>
        //而Builder也只有一个方法add，参数也是WeaponType, Supplier<Weapon>，所以这里传入map::put以代表一个Builder
        consumer.accept(map::put);
        //这里可以看出方法的返回值不影响是否能够代表builder
        consumer.accept(new TestBuilder()::build1);
        consumer.accept(new TestBuilder()::build2);
        //这里和上面的map::put一样，接收一个WeaponType返回一个Weapon，而WeaponFactory也只有一个create方法，是同样的参数和返回值
        //这里的name -> map.get(name).get()实际上就是一个WeaponFactory工厂实例
        //return name -> map.get(name).get();
        //create方法需要返回一个Weapon，因为WeaponFactory的create方法有返回值，而Builder的add方法没有返回值所以TestBuilder()::build1
        //和TestBuilder()::build2两无论有没有返回值都能表示Builder
        return new TestBuilder()::create;
    }
}
