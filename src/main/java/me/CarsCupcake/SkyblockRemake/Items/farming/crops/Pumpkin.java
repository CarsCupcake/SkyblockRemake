package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class Pumpkin extends Crop{
    @Override
    public Material getBlockType() {
        return Material.PUMPKIN;
    }

    @Override
    public String getItemId() {
        return Material.PUMPKIN.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(1,1);
    }
}
