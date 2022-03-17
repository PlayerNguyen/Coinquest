package com.github.playernguyen.coinquest.utils;

public class MathTestUtils {

    /**
     * Generate a randomized in [min,max] range.
     *
     * @param min the lower-inclusive value.
     * @param max the upper-inclusive value.
     * @return a randomized in [min,max] range.
     */
    public static double rand(double min, double max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

}
