package com.kangstant.learnjava17.jdk8.noptional;

import lombok.Data;

import java.util.Optional;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 16:36
 * @description
 */
@Data
public class DogFlat {
    private int age = 1;

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }
}
