package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;

import java.util.List;

public class ManaRegeneration extends Attribute {
    @Override
    public String name() {
        return "Mana Regeneration";
    }

    @Override
    public int maxLevel() {
        return 10;
    }

    @Override
    public boolean isAllowed(SkyblockPlayer player) {
        return true;
    }

    @Override
    public List<String> lore(int level) {
        return List.of("§7Increases your Mana Regenration", "§7by §b" + level + "%§7.");
    }
}
