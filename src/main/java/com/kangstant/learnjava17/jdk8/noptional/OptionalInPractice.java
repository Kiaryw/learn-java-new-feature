package com.kangstant.learnjava17.jdk8.noptional;

import java.util.Optional;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 16:03
 * @description
 */
public class OptionalInPractice {

    public static void main(String[] args) {
        optionalToSolveNPE();
    }

    // 传统解决 NPE 的写法
    public static void traditionalWayToSolveNPE() {
        Zoo zoo = getZoo();
        if (zoo != null) {
            Dog dog = zoo.getDog();
            if (dog != null) {
                int age = dog.getAge();
                System.out.println(age);
            }
        }
    }

    // optional 解决 NPE 的写法
    public static void optionalToSolveNPE() {
        Zoo zoo = getZoo();
        Optional.ofNullable(zoo)
                .map(Zoo::getDog)
                .map(Dog::getAge)
                .ifPresent(System.out::println);

    }

    private static Zoo getZoo() {
        return null;
    }

    public static void flat() {
        ZooFlat zooFlat = new ZooFlat();
        Optional.ofNullable(zooFlat)
                .map(ZooFlat::getDog)
                .flatMap(DogFlat::getAge)
                .ifPresent(System.out::println);
    }


}
