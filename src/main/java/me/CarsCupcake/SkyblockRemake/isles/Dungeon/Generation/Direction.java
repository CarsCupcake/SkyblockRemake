package me.CarsCupcake.SkyblockRemake.isles.Dungeon.Generation;

import jdk.dynalink.Operation;

import java.util.Set;
import java.util.function.Consumer;

public enum Direction {
    Up(location2d -> location2d.addY(1)),
    Right(location2d -> location2d.addX(1)),
    Down(location2d -> location2d.addY(-1)),
    Left(location2d -> location2d.addY(-1));
    private final Consumer<Location2d> action;

    Direction(Consumer<Location2d> action) {
        this.action = action;
    }
    public Location2d move(Location2d loc){
        action.accept(loc);
        return loc;
    }
}
