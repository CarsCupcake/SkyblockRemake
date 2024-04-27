package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FishingSpeed extends Attribute implements Listener {

    @Override
    public String name() {
        return "Fishing Speed";
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
        return List.of("§7Grants §b+" + getBuff(level) + " " + Stats.FishingSpeed);
    }
    private int getBuff(int level){
        return level*3;
    }

    @Override
    public Type[] allowed() {
        return new Type[]{Type.FishingRods};
    }

    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.FishingSpeed) return;
        for (ItemStack item : event.getPlayer().getEquipment().getArmorContents()) {
            for (AppliedAttribute attribute : getAttributes(item)) {
                if (attribute.attribute() instanceof FishingSpeed magicFind) {
                    event.addValue(magicFind.getBuff(attribute.level()));
                }
            }
        }
    }
}
