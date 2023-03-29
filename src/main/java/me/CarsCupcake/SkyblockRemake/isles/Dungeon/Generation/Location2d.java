package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import lombok.Getter;

public class Location2d {
    @Getter
    private int x;
    @Getter
    private int y;
    public Location2d(){

    }
    public Location2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Location2d l){
            return x == l.getX() && y == l.getY();
        }else return false;
    }
    @Override
    public String toString(){
        return "Location[x:" + x + ";y:" + y + "]";
    }
    @Override
    public Location2d clone() {
        return new Location2d(x,y);
    }
    public Location2d setX(int x){
        this.x = x;
        return this;
    }
    public Location2d setY(int y){
        this.y = y;
        return this;
    }
    public Location2d addX(int x){
        this.x += x;
        return this;
    }
    public Location2d addY(int y){
        this.y += y;
        return this;
    }

    public boolean isOutOfBounds(){
        return x < 0 || x > 5 || y < 0 || y > 5;
    }

    public int getMapX(){
        return (20 * x + 6);
    }
    public int getMapY(){
        return (20 * y + 6);
    }
}
