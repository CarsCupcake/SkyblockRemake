package me.CarsCupcake.SkyblockRemake.Skyblock.regions;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.utils.runnable.RunnableWithParam;

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
