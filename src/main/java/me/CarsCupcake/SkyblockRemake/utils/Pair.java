package me.CarsCupcake.SkyblockRemake.utils;

import me.CarsCupcake.SkyblockRemake.API.Bundle;

import java.util.Objects;
import java.util.function.Consumer;

public class Pair<T> extends Bundle<T, T> {
    public Pair(T t, T t2) {
        super(t, t2);
    }

    public void forSite(Consumer<T> sites){
        sites.accept(getFirst());
        sites.accept(getLast());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getLast());
    }
}
