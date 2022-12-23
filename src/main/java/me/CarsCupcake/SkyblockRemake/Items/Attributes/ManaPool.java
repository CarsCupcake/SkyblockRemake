package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;

import java.util.List;

public class ManaPool extends Attribute{
    public ManaPool(ItemType activeType, Integer level, SkyblockPlayer player) {
        super(activeType, level, player);
    }

    @Override
    public String name() {
        return "Mana Pool";
    }

    @Override
    public int maxLevel() {
        return 10;
    }

    @Override
    public boolean isAllowed() {
        return true;
    }

    @Override
    public List<String> lore() {
        return List.of("§7Grants §b+" + getBuff() + "✎ Intelligence");
    }

    @Override
    public void start() {
        player.setBaseStat(Stats.Inteligence,player.getBaseStat(Stats.Inteligence) + getBuff());
    }

    @Override
    public void stop() {
        player.setBaseStat(Stats.Inteligence,player.getBaseStat(Stats.Inteligence) - getBuff());
    }
    private int getBuff(){
        return level*20;
    }
}
