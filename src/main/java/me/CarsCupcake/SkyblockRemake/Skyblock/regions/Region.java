package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

public record Region(String name, List<IBorder> borders, RunnableWithParam<Bundle<SkyblockPlayer, Region>> onNear, RunnableWithParam<Bundle<SkyblockPlayer, Region>> onEnter, RunnableWithParam<Bundle<SkyblockPlayer, Region>> onLeave) {
    public boolean isInRegion(SkyblockPlayer player){
        for (IBorder b : borders())
            if(b.inTheBorder(player.getLocation()))
                return true;
        return false;
    }
    public double distanceToBorder(SkyblockPlayer player){
        double lowest = 100000;
        for (IBorder b : borders()){
            lowest = Math.min(lowest, b.distance(player.getLocation()));
        }
        return lowest;
    }
    public Vector getNextPoint(Location l){
        double low = borders.get(0).distance(l);
        Vector v = borders.get(0).getNearestPoint(l);
        for (IBorder b : borders) {
            double d = b.distance(l);
            if(d < low) {
                v = b.getNearestPoint(l);
                low = d;
            }
        }
        return v;
    }
    public void leave(SkyblockPlayer player){
        if(onLeave() != null) onLeave().run(new Bundle<>(player, this));
    }
    public void enter(SkyblockPlayer player){
        if(onEnter() != null) onEnter().run(new Bundle<>(player, this));
    }
    public void near(SkyblockPlayer player){
        if(onNear() != null) onNear().run(new Bundle<>(player, this));
    }
    public static class BasicEnter implements RunnableWithParam<SkyblockPlayer> {

        @Override
        public void run(SkyblockPlayer player) {
            //TODO add basic enter thing
        }
    }
}
