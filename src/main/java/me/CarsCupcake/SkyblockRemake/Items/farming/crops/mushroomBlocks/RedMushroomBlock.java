package me.CarsCupcake.SkyblockRemake.Items.farming.crops.mushroomBlocks;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.farming.crops.Crop;
import org.bukkit.Material;

public class RedMushroomBlock extends Crop {
    @Override
    public Material getBlockType() {
        return Material.RED_MUSHROOM_BLOCK;
    }

    @Override
    public String getItemId() {
        return Material.RED_MUSHROOM.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(1,2);
    }
}
