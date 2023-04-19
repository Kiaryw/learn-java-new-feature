package com.kangstant.learnjava17.jdk8.nlambda;

import javax.swing.*;
import java.util.*;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 13:53
 * @description
 */
public class LambdaInPractice {
    public static void main(String[] args) {

    }

    // 1. 替代匿名内部类
    // 过去给方法传递动态参数的唯一方法是使用内部类。现在可以用lambda表达式来代替匿名内部类。
    // 例子1.1 Runnable接口
    public static void runnableCase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类实现 Runnable");
            }
        }).start();

        new Thread(() -> System.out.println("Lambda 表达式实现 Runnable")).start();
    }

    // 例子1.2 Comparator接口
    public static void comparatorCase() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // Lambda
        Collections.sort(integers, (o1, o2) -> o1 - o2);

        // 分解
        Comparator<Integer> comparator = (o1, o2) -> o1 - o2;
        Collections.sort(integers, comparator);

        System.out.println(integers);
    }

    // 例子1.3 自定义接口: 只要方法的参数是函数式接口都可以用 Lambda 表达式。
    // LambdaClass

    // 2. 集合迭代
    void lambdaFor() {
        List<String> strings = Arrays.asList("1", "2", "3");

        // 传统foreach
        for (String s : strings) {
            System.out.println(s);
        }

        // Lambda foreach
        strings.forEach((s) -> System.out.println(s));
        //or
        strings.forEach(System.out::println);

        //map
        Map<Integer, String> map = new HashMap<>();
        map.forEach((k, v) -> System.out.println(v));

    }

    // 3. 方法引用
    // Java 8 允许使用 :: 关键字来传递方法或者构造函数引用，
    // 无论如何，表达式返回的类型必须是 functional-interface。

    // 4. lambda 表达式可以引用外边变量，但是该变量默认拥有 final 属性，不能被修改，如果修改，编译时就报错。
    void testI() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        int i = 0;
        Collections.sort(integers, (o1, o2) -> o1 - i);
    }


}
