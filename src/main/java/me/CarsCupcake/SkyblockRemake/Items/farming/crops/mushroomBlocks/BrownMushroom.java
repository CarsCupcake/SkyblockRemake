package me.CarsCupcake.SkyblockRemake.Items.farming.crops.mushroomBlocks;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.farming.crops.Crop;
import org.bukkit.Material;

public class BrownMushroom extends Crop {
    @Override
    public Material getBlockType() {
        return Material.BROWN_MUSHROOM;
    }

    @Override
    public String getItemId() {
        return Material.BROWN_MUSHROOM.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(1,2);
    }
}
