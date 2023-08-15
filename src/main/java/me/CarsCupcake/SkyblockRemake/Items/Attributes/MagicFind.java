package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class MagicFind extends Attribute implements Listener {
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

    private double getBuff() {
        return level * 0.5;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.MagicFind) return;
        for (Attribute attribute : getAttributes(event.getItem())) {
            if (attribute instanceof MagicFind magicFind) {
                event.addValue(magicFind.getBuff());
            }
        }
    }
}
