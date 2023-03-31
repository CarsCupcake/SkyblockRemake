package me.CarsCupcake.SkyblockRemake.utils.maps;

import org.apache.commons.lang.Validate;

import java.util.HashMap;

public class CountMap<T> extends HashMap<T, Integer> {
    public void add(T type, int amount){
        Validate.isTrue(super.containsKey(type));
        super.replace(type, super.get(type) + amount);
    }
    public void add(T type, int amount, int base){
        if(super.containsKey(type))
            add(type, amount);
        else super.put(type, amount + base);
    }
}
