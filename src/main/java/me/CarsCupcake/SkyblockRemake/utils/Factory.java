package me.CarsCupcake.SkyblockRemake.utils;

public interface Factory<T, K> {
    K factor(T obj);
}
