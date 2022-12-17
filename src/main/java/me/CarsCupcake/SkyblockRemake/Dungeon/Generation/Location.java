package me.CarsCupcake.SkyblockRemake.Dungeon.Generation;

import lombok.Getter;
import lombok.Setter;

public class Location {
    @Getter@Setter
    private int x;
    @Getter@Setter
    private int y;
    public Location(){

    }
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Location l){
            return x == l.getX() && y == l.getY();
        }else return false;
    }
}
