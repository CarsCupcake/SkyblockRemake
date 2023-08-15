package me.CarsCupcake.SkyblockRemake.utils.maps;


import java.util.HashMap;

public class CountMap<T> extends HashMap<T, Integer> {
    public void add(T type, int amount) {
        if (super.containsKey(type))
            super.replace(type, super.get(type) + amount);
        else put(type, amount);
    }

    public void add(T type, int amount, int base) {
        if (super.containsKey(type))
            add(type, amount);
        else super.put(type, amount + base);
    }

    public void addAll(int amount) {
        for (T t : keySet())
            add(t, amount);
    }

    public void filterByAmount(int amount) {
        for (T t : keySet()) {
            if (amount != get(t)) remove(t);
        }
    }

    public void removeByAmount(int amount) {
        for (T t : keySet()) {
            if (amount == get(t)) remove(t);
        }
    }
}
