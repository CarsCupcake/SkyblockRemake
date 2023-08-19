package me.CarsCupcake.SkyblockRemake.Slayer;

import me.CarsCupcake.SkyblockRemake.utils.loot.LootTable;

public class SlayerLootTable extends LootTable {
    @Override
    public String chanceString(double chance) {
        if(chance < 0.0006) return "§d§lPray RNGesus";
        if(chance < 0.006) return "§d§lPray RNGesus";
        if(chance < 0.02) return "§5Extraordinary";
        if(chance < 0.2) return "§bRare";
        return "§9Occasional";
    }
}
