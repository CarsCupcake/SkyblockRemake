package me.CarsCupcake.SkyblockRemake.MiningSystem.Blocks;


import me.CarsCupcake.SkyblockRemake.MiningSystem.MiningBlock;
import org.bukkit.Material;

public class Stone extends MiningBlock {

    @Override
    public int blockStrength() {
        return 1;
    }

    @Override
    public Material getType() {
        return Material.STONE;
    }

    @Override
    public int getBreakingPower() {
        return 15;
    }
}
