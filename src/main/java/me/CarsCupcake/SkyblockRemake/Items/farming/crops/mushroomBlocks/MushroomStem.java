package me.CarsCupcake.SkyblockRemake.Items.farming.crops.mushroomBlocks;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import me.CarsCupcake.SkyblockRemake.Items.farming.crops.Crop;
import org.bukkit.Material;

public class MushroomStem extends Crop {
    @Override
    public Material getBlockType() {
        return Material.MUSHROOM_STEM;
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
