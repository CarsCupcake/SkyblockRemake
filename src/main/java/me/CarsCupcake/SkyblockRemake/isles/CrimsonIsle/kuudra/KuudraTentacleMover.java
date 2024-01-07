package me.CarsCupcake.SkyblockRemake.isles.CrimsonIsle.kuudra;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KuudraTentacleMover {
    private static final int maxIterations = 50;
    private static final double minDist = 1d;
    // Creddit: https://www.youtube.com/watch?v=PGk0rnyTa1U
    public static List<Location> calculateTentaclePositions(List<Location> locs, Vector tipNew) {
        List<Vector> vecs = locs.stream().map(Location::toVector).collect(Collectors.toList());
        Vector origin = vecs.get(0);
        List<Double> length = new ArrayList<>(locs.size() - 1);
        for (int i = 0; i < locs.size() - 1; i++) {
            length.add(locs.get(i + 1).toVector().clone().subtract(locs.get(i).toVector()).length());
        }

        for (int it = 0; it < maxIterations; it++) {
            boolean fromTarget = it % 2 == 0;
            Collections.reverse(vecs);
            Collections.reverse(length);
            vecs.set(0, (fromTarget) ? tipNew : origin);

            for (int i = 1; i < locs.size(); i++) {
                Vector dir = vecs.get(i).clone().subtract(vecs.get(i - 1));
                if (dir.length() != 0) dir.normalize();
                vecs.set(i, vecs.get(i - 1).clone().add(dir.multiply(length.get(i - 1))));
            }
            double dstToTarget = vecs.get(vecs.size() - 1).clone().subtract(tipNew).length();
            if (!fromTarget && dstToTarget <= minDist)
                return vecs.stream().map(vector -> vector.toLocation(locs.get(0).getWorld())).toList();
        }
        return vecs.stream().map(vector -> vector.toLocation(locs.get(0).getWorld())).toList();
    }
    public static void moveTentacle(KuudraTentacle tentacle, Location target) {
        List<Location> locs = calculateTentaclePositions(tentacle.getEntities().stream().map(Entity::getLocation).toList(), target.toVector());
        int i = 0;
        for (Location location : locs) {
            tentacle.getEntities().get(i).teleport(location);
            i++;
        }
    }
}
