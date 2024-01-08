package me.CarsCupcake.SkyblockRemake.utils.math;

import me.CarsCupcake.SkyblockRemake.Main;
import org.bukkit.util.Vector;

public record Line(Vector locationVektor, Vector directionVec) {
    public Vector getNearestPoint(Vector v) {
        double s = ((locationVektor.getX() - v.getX()) * directionVec.getX() + (locationVektor.getY() - v.getY()) * directionVec.getY() + (locationVektor.getZ() - v.getZ()) * directionVec.getZ()) /
                (Math.pow(directionVec.getX(), 2) + Math.pow(directionVec.getY(), 2) + Math.pow(directionVec.getZ(), 2));
        return calcPoint(-s);
    }
    public Vector calcPoint(double d) {
        return locationVektor.clone().add(directionVec.clone().multiply(d));
    }
}
