package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation;

import me.CarsCupcake.SkyblockRemake.utils.Assert;

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
    public Direction spinRight(int rotation){
        Assert.isTrue(rotation > 0, "rotation is illegale");
        Direction d = this;
        for (int i = 0; i < rotation; i++)
            d = switch (this){
                case Up -> Right;
                case Right -> Down;
                case Down -> Left;
                case Left -> Up;
            };
        return d;
    }
    public Direction spinRight(){
        return switch (this){
            case Up -> Right;
            case Right -> Down;
            case Down -> Left;
            case Left -> Up;
        };
    }
    public Direction spinLeft(int rotation){
        Assert.isTrue(rotation > 0, "rotation is illegale");
        Direction d = this;
        for (int i = 0; i < rotation; i++)
            d = switch (this){
                case Up -> Left;
                case Right -> Up;
                case Down -> Right;
                case Left -> Down;
            };
        return d;
    }
    public Direction spinLeft(){
        return switch (this){
            case Up -> Left;
            case Right -> Up;
            case Down -> Right;
            case Left -> Down;
        };
    }
}
