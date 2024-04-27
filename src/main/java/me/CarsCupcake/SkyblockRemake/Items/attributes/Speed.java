package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.ItemEvents.GetStatFromItemEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class Speed extends Attribute implements Listener {

    @Override
    public String name() {
        return "Speed";
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
        return List.of("§7Grants §f+" + getBuff(l) + " " + Stats.Speed.getSymbol() + " Speed");
    }
    private int getBuff(int level){
        return level*5;
    }
    @Override
    public Type[] allowed() {
        return new Type[]{Type.Armor, Type.Equipment};
    }

    @EventHandler
    public void onStatGet(GetStatFromItemEvent event) {
        if (event.getStat() != Stats.Speed) return;
        for (AppliedAttribute attribute : getAttributes(event.getItem())) {
            if (attribute.attribute() instanceof Speed magicFind) {
                event.addValue(magicFind.getBuff(attribute.level()));
            }
        }
    }
}
