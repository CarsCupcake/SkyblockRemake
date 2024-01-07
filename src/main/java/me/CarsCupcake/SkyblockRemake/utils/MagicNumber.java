package me.CarsCupcake.SkyblockRemake.utils;

public final class MagicNumber {
    public static double milion(double d) {
        return Math.pow(10, 6) * d;
    }
    public static double thousand(double d) {
        return Math.pow(10, 3) * d;
    }
    public static double billion(double d) {
        return Math.pow(10, 9) * d;
    }
}
