package me.CarsCupcake.SkyblockRemake.Items.farming.crops;

import me.CarsCupcake.SkyblockRemake.API.Bundle;
import org.bukkit.Material;

public class Melon extends Crop{
    @Override
    public Material getBlockType() {
        return Material.MELON;
    }

    @Override
    public String getItemId() {
        return Material.MELON_SLICE.toString();
    }

    @Override
    protected Bundle<Integer, Integer> getDropAmounts() {
        return new Bundle<>(3,7);
    }
}
