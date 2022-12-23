package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Stats;

import java.util.List;

public class MagicFind extends Attribute{
    public MagicFind(ItemType activeType, Integer level, SkyblockPlayer player) {
        super(activeType, level, player);
    }

    @Override
    public String name() {
        return "Magic Find";
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
        return List.of("§7Grants §b+" + ((getBuff() % 1 == 0) ? String.format("%.0f", getBuff()) : getBuff()) + "✯ Magic Find");
    }

    @Override
    public void start() {
        player.setBaseStat(Stats.MagicFind,player.getBaseStat(Stats.MagicFind) + getBuff());
    }

    @Override
    public void stop() {
        player.setBaseStat(Stats.MagicFind,player.getBaseStat(Stats.MagicFind) - getBuff());
    }
    private double getBuff(){
        return level*0.5;
    }
}
