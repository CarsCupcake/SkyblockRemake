package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class SugarCane extends Crop{
    @Override
    public Material getBlockType() {
        return Material.SUGAR_CANE;
    }

    @Override
    public String getItemId() {
        return Material.SUGAR_CANE.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(1,1);
    }
}
