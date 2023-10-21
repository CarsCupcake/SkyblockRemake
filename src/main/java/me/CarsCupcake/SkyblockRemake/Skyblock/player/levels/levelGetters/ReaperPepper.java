package me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.levelGetters;

import me.CarsCupcake.SkyblockRemake.configs.ExtraInformations;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.player.levels.SkyblockLevelsGetter;

import java.util.HashMap;

public class ReaperPepper implements SkyblockLevelsGetter {
    public static final HashMap<SkyblockPlayer, ReaperPepper> peppers = new HashMap<>();
    private final SkyblockPlayer player;
    public ReaperPepper(SkyblockPlayer player){
        this.player = player;
        peppers.put(player, this);
    }
    @Override
    public int getSkyblockXp() {
        return ExtraInformations.get().getInt(player.getUniqueId() + ".reaperpepper", 0) * 10;
    }

    @Override
    public int getMaxSkyblockXp() {
        return 50;
    }

    @Override
    public String getName() {
        return "Reaper Pepper";
    }
}
