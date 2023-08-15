package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class FishingSpeed extends Attribute implements Listener {
    public FishingSpeed(ItemType activeType, Integer level, SkyblockPlayer player) {
        super(activeType, level, player);
    }

    @Override
    public String name() {
        return "Fishing Speed";
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
        return List.of("§7Grants §b+" + getBuff() + Stats.FishingSpeed.getSymbol() + " Fishing Speed");
    }
    private int getBuff(){
        return level*3;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.FishingSpeed) return;
        for (Attribute attribute : getAttributes(event.getItem())) {
            if (attribute instanceof FishingSpeed magicFind) {
                event.addValue(magicFind.getBuff());
            }
        }
    }
}
