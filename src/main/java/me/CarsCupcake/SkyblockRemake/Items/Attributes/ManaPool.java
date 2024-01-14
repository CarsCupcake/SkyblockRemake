package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class ManaPool extends Attribute implements Listener {

    @Override
    public String name() {
        return "Mana Pool";
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
    public List<String> lore(int l) {
        return List.of("§7Grants §b+" + getBuff(l) + "✎ Intelligence");
    }
    private int getBuff(int level){
        return level*20;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.Inteligence) return;
        for (AppliedAttribute attribute : getAttributes(event.getItem())) {
            if (attribute.attribute() instanceof ManaPool magicFind) {
                event.addValue(magicFind.getBuff(attribute.level()));
            }
        }
    }
}
