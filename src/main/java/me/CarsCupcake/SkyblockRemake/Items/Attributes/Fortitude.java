package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Items.ItemType;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class Fortitude extends Attribute implements Listener {
    public Fortitude(ItemType activeType, Integer level, SkyblockPlayer player) {
        super(activeType, level, player);
    }

    @Override
    public String name() {
        return "Fortitude";
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
        return List.of("Increases§c" + Stats.Health.getSymbol() + " Health§a" + Stats.Defense.getSymbol() + " Defense§7 by §a" + getBuff());
    }
    private double getBuff(){
        return level*0.5;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.Health && event.getStat() != Stats.Defense) return;
        for (Attribute attribute : getAttributes(event.getItem())) {
            if (attribute instanceof Fortitude magicFind) {
                event.addValue(magicFind.getBuff());
            }
        }
    }
}
