package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.List;

public class RegionCuboid implements IBorder{
    private final double minX;
    private final double minY;
    private final double minZ;
    private final double maxX;
    private final double maxY;
    private final double maxZ;
    private final Vector v1;
    private final Vector v2;

    public RegionCuboid(Vector v1, Vector v2){
        minX = Math.min(v1.getX(), v2.getX());
        minY = Math.min(v1.getY(), v2.getY());
        minZ = Math.min(v1.getZ(), v2.getZ());
        maxX = Math.max(v1.getX(), v2.getX());
        maxY = Math.max(v1.getY(), v2.getY());
        maxZ = Math.max(v1.getZ(), v2.getZ());
        this.v1 = v1;
        this.v2 = v2;
    }
    @Override
    public double distance(Location location) {
        return getNearestPoint(location).length();
    }
    public Vector getNearestPoint(Location location){
        Vector x = new Vector(maxX - minX, 0, 0);
        Vector y = new Vector(0, maxY - minY, 0);
        Vector z = new Vector(0, 0, maxZ - minZ);
        Plane p = new Plane(y.clone(), z.clone(), v2);
        double dis = p.nextPoint(location.toVector()).subtract(location.toVector()).length();
        for (Plane plane : List.of(new Plane(x.clone(), y.clone(), v1), new Plane(x.clone(), z.clone(), v1),
                new Plane(y.clone(), z.clone(), v1), new Plane(x.clone(), y.clone(), v2), new Plane(x.clone(), z.clone(), v2))){
            double distance = plane.nextPoint(location.toVector()).subtract(location.toVector()).length();
            if(distance < dis){
                p = plane;
                dis = distance;
            }
        }
        return p.nextPoint(location.toVector());
    }

    @Override
    public boolean inTheBorder(Location location) {
        return location.getX() >= minX && location.getX() <= maxX && location.getY() >= minY && location.getY() <= maxY && location.getZ() >= minZ && location.getZ() <= maxZ;
    }
    public BoundingBox toBoundingBox(){
        return new BoundingBox(maxX, maxY, maxZ, minX, minY, minZ);
    }
    record Plane(Vector d1, Vector d2, Vector p){
        public Vector nextPoint(Vector v){
            Vector norm = d1().clone().crossProduct(d2());
            double p3 = v.clone().subtract(p().clone()).dot(norm);
            double a = -p3 / norm.dot(norm);
            return p().clone().add(norm.clone().multiply(a));
        }
    }
}
