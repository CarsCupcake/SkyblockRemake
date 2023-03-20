package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class Wheat extends Crop{
    @Override
    public Material getBlockType() {
        return Material.WHEAT;
    }

    @Override
    public String getItemId() {
        return Material.WHEAT.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(2,4);
    }
}
