package me.CarsCupcake.SkyblockRemake.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class StandUtils {
    //+3 in the dirs


    public static Location loc1;
    public static Location loc2;
    public static Location loc3;
    public static Location loc4;

    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {

        List<Location> circleBlocks = new ArrayList<>();

        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();

        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {

                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {

                        Location l = new Location(centerBlock.getWorld(), x, centerBlock.getY(), z);

                        if(x-3 == centerBlock.getBlockX() && z == centerBlock.getBlockZ())
                            loc1 = l;
                        if(x+3 == centerBlock.getBlockX() && z == centerBlock.getBlockZ())
                            loc3 = l;
                        if(z-3 == centerBlock.getBlockZ() && x == centerBlock.getBlockX())
                            loc2 = l;
                        if(z+3 == centerBlock.getBlockZ() && x == centerBlock.getBlockX())
                            loc4 = l;

                        if(!circleBlocks.contains(l)) circleBlocks.add(l);
                    }

                }
            }
        }

        return circleBlocks;
    }


}
