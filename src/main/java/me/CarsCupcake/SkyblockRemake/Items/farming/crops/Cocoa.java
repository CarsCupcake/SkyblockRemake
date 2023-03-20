package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class Cocoa extends Crop{
    @Override
    public Material getBlockType() {
        return Material.COCOA;
    }

    @Override
    public String getItemId() {
        return Material.COCOA_BEANS.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(2,3);
    }
}
