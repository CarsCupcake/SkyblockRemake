package me.CarsCupcake.SkyblockRemake.isles.Dungeon.generation;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class Location2d implements Cloneable {
    @Getter
    private int x;
    @Getter
    private int y;

    public Location2d() {

    }

    public Location2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Location2d l) {
            return x == l.getX() && y == l.getY();
        } else return obj == this;
    }

    @Override
    public String toString() {
        return "Location2d[x:" + x + ";y:" + y + "]";
    }

    @Override
    public Location2d clone() {
        Location2d c;
        try {
            c = (Location2d) super.clone();
        } catch (CloneNotSupportedException cantHappen) {
            throw new InternalError("Bad clone from Location2d");
        }
        c.x = x;
        c.y = y;
        return c;
    }

    public Location2d setX(int x) {
        this.x = x;
        return this;
    }

    public Location2d setY(int y) {
        this.y = y;
        return this;
    }

    public Location2d addX(int x) {
        this.x += x;
        return this;
    }

    public Location2d addY(int y) {
        this.y += y;
        return this;
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 5 || y < 0 || y > 5;
    }

    public int getMapX() {
        return x * 32;
    }

    public int getMapY() {
        return y * 32;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Location asLocation(World world, double y) {
        return new Location(world, x,y, this.y);
    }
}
