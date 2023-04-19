package com.kangstant.learnjava17.jdk8.ninterface.impl;

import com.kangstant.learnjava17.jdk8.ninterface.InterfaceNew;
import com.kangstant.learnjava17.jdk8.ninterface.InterfaceNew1;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 11:37
 * @description
 */
public class InterfaceNewImpl implements InterfaceNew, InterfaceNew1 {

    // 如果有一个类既实现了 InterfaceNew 接口又实现了 InterfaceNew1接口，
    // 它们都有def()，并且 InterfaceNew 接口和 InterfaceNew1接口没有继承关系的话，这时就必须重写def()。
    @Override
    public void def() {
        InterfaceNew.super.def();
    }

    @Override
    public void f() {
        System.out.println("实现类重写");
    }

    public static void main(String[] args) {
        InterfaceNewImpl impl = new InterfaceNewImpl();
        impl.def();
        InterfaceNew.sm();
    }
}
