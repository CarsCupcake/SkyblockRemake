package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class Potato extends Crop{
    @Override
    public Material getBlockType() {
        return Material.POTATOES;
    }

    @Override
    public String getItemId() {
        return Material.POTATO.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(2,4);
    }
}
