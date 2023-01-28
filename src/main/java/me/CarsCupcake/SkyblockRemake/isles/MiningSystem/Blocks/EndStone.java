package me.CarsCupcake.SkyblockRemake.isles.MiningSystem.Blocks;

import me.CarsCupcake.SkyblockRemake.isles.MiningSystem.MiningBlock;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EndStone extends MiningBlock {
    @Override
    public int blockStrength() {
        return 30;
    }

    @Override
    public int getInstaMineSpeed() {
        return 1800;
    }

    @Override
    public Material getType() {
        return Material.END_STONE;
    }

    @Override
    public int getBreakingPower() {
        return 1;
    }

    @Override
    public ArrayList<ItemStack> getDrops(SkyblockPlayer player) {
        return new ArrayList<>(List.of(new ItemStack(Material.END_STONE)));
    }
}
