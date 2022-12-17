package me.CarsCupcake.SkyblockRemake.Dungeon.Generation;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class LocationMap extends HashMap<Location, Room> {
    @Getter
    private Collection<Location> mains = new HashSet<>();
    @Getter
    private Collection<Location> subs = new HashSet<>();
    @Getter@Setter
    private Room green;
    @Getter@Setter
    private Room red;
    @Getter@Setter
    private Room fairy;
    public Collection<Location> getByX(int x){
        Set<Location> keySet = this.keySet();
        keySet.add(green.getLocation());
        keySet.add(red.getLocation());
        keySet.add(fairy.getLocation());
        return keySet.stream().filter(t -> t.getX() == x).toList();
    }
    public Collection<Location> getByY(int y){
        Set<Location> keySet = this.keySet();
        keySet.add(green.getLocation());
        keySet.add(red.getLocation());
        keySet.add(fairy.getLocation());
        return keySet.stream().filter(t -> t.getY() == y).toList();
    }
    @Override
    public Room put(Location l, Room k){
        if(k.isSub())
            subs.add(l);
        else
            mains.add(l);
        return super.put(l, k);
    }
    @Override
    public boolean containsKey(Object key){
        return super.containsKey(key) || green.getLocation().equals(key) || red.getLocation().equals(key) || fairy.getLocation().equals(key);
    }
}
