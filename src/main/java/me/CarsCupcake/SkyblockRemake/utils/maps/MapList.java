package me.CarsCupcake.SkyblockRemake.utils.maps;

import me.CarsCupcake.SkyblockRemake.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapList<T, K> extends HashMap<T, List<K>> {
    public void add(T key, K value) {
        List<K> ks;
        if(super.containsKey(key)){
            ks = super.get(key);
        }else ks = new ArrayList<>();
        ks.add(value);
        put(key, ks);
    }

    public void removeFromList(T key, K value) {
        List<K> ks = super.get(key);
        ks.remove(value);
        if(ks.isEmpty()) super.remove(key);
        else super.put(key, ks);
    }

    @Override
    public List<K> get(Object key) {
        List<K> k = super.get(key);
        if(k == null) k = new ArrayList<>();
        return k;
    }
}
