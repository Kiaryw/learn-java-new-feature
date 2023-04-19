package com.kangstant.learnjava17.jdk8.nOptional;

import lombok.Data;

/**
 * @author spencer.huang@nio.com
 * @date 2023/4/19 16:37
 * @description
 */
@Data
public class ZooFlat {
    private DogFlat dog = new DogFlat();

    public DogFlat getDog() {
        return dog;
    }
}
