package com.kangstant.learnjava17.jdk8.ninterface;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 11:39
 * @description
 */
public interface InterfaceNew1 {
    default void def() {
        System.out.println("InterfaceNew1 default方法");
    }
}
