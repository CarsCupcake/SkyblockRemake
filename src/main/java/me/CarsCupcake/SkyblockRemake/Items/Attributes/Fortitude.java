package me.CarsCupcake.SkyblockRemake.Items.Attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class Fortitude extends Attribute implements Listener {

    @Override
    public String name() {
        return "Fortitude";
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
        return List.of("§7Increases§c" + Stats.Health.getSymbol() + " Health§a" + Stats.Defense.getSymbol() + " Defense§7 by §a" + getBuff(l));
    }
    private double getBuff(int level){
        return level*0.5;
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.Health && event.getStat() != Stats.Defense) return;
        for (AppliedAttribute attribute : getAttributes(event.getItem())) {
            if (attribute.attribute() instanceof Fortitude magicFind) {
                event.addValue(magicFind.getBuff(attribute.level()));
            }
        }
    }
}
