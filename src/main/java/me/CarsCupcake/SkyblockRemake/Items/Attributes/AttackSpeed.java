package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class AttackSpeed extends Attribute implements Listener {
    public AttackSpeed(ItemType activeType, Integer level, SkyblockPlayer player) {
        super(activeType, level, player);
    }

    @Override
    public String name() {
        return "Attack Speed";
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
        return List.of("Grants §e+" + getBuff() + Stats.AttackSpeed.getSymbol() + " Bonus Attack Speed§7.");
    }
    private double getBuff(){
        return level;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.AttackSpeed) return;
        for (Attribute attribute : getAttributes(event.getItem())) {
            if (attribute instanceof AttackSpeed magicFind) {
                event.addValue(magicFind.getBuff());
            }
        }
    }
}
