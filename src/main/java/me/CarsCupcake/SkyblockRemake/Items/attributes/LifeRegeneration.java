package me.CarsCupcake.SkyblockRemake.Items.attributes;

import me.CarsCupcake.SkyblockRemake.API.PlayerEvent.GetTotalStatEvent;
import me.CarsCupcake.SkyblockRemake.Skyblock.SkyblockPlayer;
import me.CarsCupcake.SkyblockRemake.Skyblock.Stats;
import me.CarsCupcake.SkyblockRemake.utils.Tools;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LifeRegeneration extends Attribute implements Listener {

    @Override
    public String name() {
        return "Life Regeneration";
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
        return List.of("§7Grants " + Tools.cleanDouble(getBuff(l)) + Stats.HealthRegen);
    }

    private double getBuff(int l) {
        return l * 1.25;
    }
    @Override
    public Type[] allowed() {
        return new Type[]{Type.Armor, Type.Equipment};
    }

    @EventHandler
    public void onStatGet(GetTotalStatEvent event) {
        if (event.getStat() != Stats.HealthRegen) return;
        for (ItemStack item : event.getPlayer().getEquipment().getArmorContents()) {
            for (AppliedAttribute attribute : getAttributes(item)) {
                if (attribute.attribute() instanceof LifeRegeneration magicFind) {
                    event.addValue(magicFind.getBuff(attribute.level()));
                }
            }
        }
    }
}
