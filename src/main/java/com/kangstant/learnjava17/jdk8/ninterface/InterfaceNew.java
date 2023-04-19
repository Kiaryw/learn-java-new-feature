package com.kangstant.learnjava17.jdk8.ninterface;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 11:34
 * @description
 */
public interface InterfaceNew {

    static void sm() {
        System.out.println("interface提供的方式实现");
    }
    static void sm2() {
        System.out.println("interface提供的方式实现");
    }

    default void def() {
        System.out.println("interface default方法");
    }
    default void def2() {
        System.out.println("interface default2方法");
    }
    // 必须要实现类重写
    void f();
}
