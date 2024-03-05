package me.CarsCupcake.SkyblockRemake.utils.maps;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CountMap<T> extends HashMap<T, Integer> {
    public void add(T type, int amount) {
        if (super.containsKey(type))
            super.replace(type, super.get(type) + amount);
        else put(type, amount);
    }
    public void subtract(T type, int amount) {
        add(type, -amount);
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

    public void subtractAll(int amount) {
        addAll(-amount);
    }

    public List<T> getByAmount(int amount) {
        List<T> l = new ArrayList<>();
        for (T t : keySet()) {
            if (amount == get(t)) l.add(t);
        }
        return l;
    }

    public void removeByAmount(int amount) {
        for (T t : Set.copyOf(keySet())) {
            if (amount == get(t)) remove(t);
        }
    }
}
