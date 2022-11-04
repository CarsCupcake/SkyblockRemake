package me.CarsCupcake.SkyblockRemake.API;

public class  Bundle <T,K>{
    private final T t;
    private final K k;
    public  Bundle(T t, K k) {
        this.t = t;
        this.k = k;
    }
    public T getFirst() {
        return t;
    }
    public K getLast() {
        return k;

    }
}
