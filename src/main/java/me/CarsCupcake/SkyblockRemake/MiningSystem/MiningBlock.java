package me.CarsCupcake.SkyblockRemake.MiningSystem;

import me.CarsCupcake.SkyblockRemake.Main;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class MiningBlock {

    public abstract int blockStrength();
    public abstract Material getType();
    public abstract int getBreakingPower();
    public  boolean isThisBlock(Block block){
        return block.getType().equals(getType());
    }
    public  double getSoftCap() {
        return Tools.round(6.66666666666666666666666666666666666*blockStrength(), 0);
    }

    public int getMiningTicks(SkyblockPlayer player){
        double mining_speed = Main.getPlayerMiningSpeed(player);
        double SoftCap= getSoftCap();
        if(SoftCap <= mining_speed)
            mining_speed = SoftCap;

        double MiningTime = (blockStrength() * 30)/mining_speed;
        return (int) MiningTime;
    }


}
